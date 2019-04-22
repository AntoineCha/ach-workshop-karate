package info.ach.karate.gateway.controller;

import info.ach.karate.gateway.dto.JsonResponseDto;
import info.ach.karate.gateway.exception.NotFoundException;
import info.ach.karate.gateway.exception.TimeoutException;
import info.ach.karate.gateway.model.Cart;
import info.ach.karate.gateway.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping
    public JsonResponseDto<Cart> create() {
        return new JsonResponseDto(cartService.create());
    }

    @GetMapping("/{uuid}")
    public JsonResponseDto<Cart> get(@PathVariable String uuid) {
        return new JsonResponseDto(cartService.get(uuid));
    }

    @PostMapping("/{uuid}/article")
    public ResponseEntity<JsonResponseDto<Cart>> addArticle(@PathVariable String uuid, @RequestBody String ean) {
        try {
            return new ResponseEntity(new JsonResponseDto(cartService.addArticle(uuid, ean)), HttpStatus.OK) ;
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (TimeoutException e) {
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
