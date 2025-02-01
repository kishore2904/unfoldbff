package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.CartDto;
import com.unfold.unfoldbff.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class CartController {

    private final CartServiceImpl cartServiceImpl;

    public CartController(CartServiceImpl cartServiceImpl) {
        this.cartServiceImpl = cartServiceImpl;
    }

    @GetMapping("/cartProducts")
    public ResponseEntity<List<CartDto>> findCartItemsByUserId(@RequestParam Integer userId) {
        return ResponseEntity.ok(cartServiceImpl.getAllCartItemsByUserId(userId));
    }

    @PostMapping("/cartProducts")
    ResponseEntity<Void> saveCategories(@RequestBody CartDto cartDto) {
        cartServiceImpl.addProductsOrModifyQuantityOfProductToCart(cartDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/cart/{cartId}")
    ResponseEntity<Void> deleteByCartId(@PathVariable Integer cartId) {
        cartServiceImpl.delete(cartId);
        return ResponseEntity.noContent().build();

    }
}
