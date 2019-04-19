package info.ach.karate.gateway.controller;

import info.ach.karate.gateway.dto.JsonResponseDto;
import info.ach.karate.gateway.model.Cart;
import info.ach.karate.gateway.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JsonResponseDto<Cart> addArticle(@PathVariable String uuid, @RequestBody String ean) {
        return new JsonResponseDto(cartService.addArticle(uuid, ean));
    }

}
