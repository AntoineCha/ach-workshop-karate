package info.ach.karate.gateway.dto;

public class JsonResponseDto<T> {

    private T result;

    private String code;
    private String detail;

    public JsonResponseDto() {
    }

    public JsonResponseDto(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
