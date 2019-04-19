package info.ach.karate.gateway;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.intuit.karate.KarateOptions;
import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@CucumberOptions(tags = "~@ignore", features = {
        "classpath:info/ach/karate/gateway/usecaseone/cart_create.feature",
        "classpath:info/ach/karate/gateway/usecasetwo/cart_create.feature"
})
public class KarateRunner {


    private static ConfigurableApplicationContext context;

    private WireMockServer wireMockServer;

    @Before
    public void setUp(){
        /*
        wireMockServer = new WireMockServer(wireMockConfig().port(10000).notifier(new Slf4jNotifier(true)));
        wireMockServer.start();

        WireMock.configureFor("localhost", 10000);
        */

    }


    @Test
    public void testParallel() {
        context = SpringApplication.run(Application.class, new String[]{"--server.port=18080"});

        String karateOutputPath = "target/surefire-reports";
        KarateStats stats = CucumberRunner.parallel(getClass(), 1, karateOutputPath);
        generateReport(karateOutputPath);
        assertTrue("there are scenario failures", stats.getFailCount() == 0);
    }

    private static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "Ulys cart karate tests");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }

    @AfterClass
    public static void setDown() {
        if(context != null) {
            context.close();
        }
    }
}
