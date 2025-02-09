package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.WishlistDto;
import com.unfold.unfoldbff.service.impl.WishlistServiceImpl;
import com.unfold.unfoldbff.mapper.WishlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.unfold.unfoldbff.utils.Constants.PROD_URL;

@RestController
@RequestMapping(value = "/rest/unfold")
@CrossOrigin(value = PROD_URL)
public class WishlistController {

    private final WishlistServiceImpl wishlistService;
    private final WishlistMapper wishlistMapper;

    @Autowired
    public WishlistController(WishlistServiceImpl wishlistService, WishlistMapper wishlistMapper) {
        this.wishlistService = wishlistService;
        this.wishlistMapper = wishlistMapper;
    }

    // Add product to wishlist
    @PostMapping("/addWishlist")
    public ResponseEntity<WishlistDto> addProductToWishlist(@RequestParam Long userId, @RequestParam Long productId) {
        WishlistDto wishlistDTO = wishlistService.addProductToWishlist(userId, productId);
        return ResponseEntity.ok(wishlistDTO);
    }

    // Get wishlist for a user
    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishlist(@PathVariable Long userId) {
        List<WishlistDto> wishlistDTOs = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlistDTOs);
    }

    // Edit the status of a wishlist item
    @PutMapping("/edit/{wishlistId}")
    public ResponseEntity<WishlistDto> editWishlistItemStatus(@PathVariable Long wishlistId, @RequestParam String status) {
        WishlistDto wishlistDTO = wishlistService.editWishlistItemStatus(wishlistId, status);
        return ResponseEntity.ok(wishlistDTO);
    }

    // Remove product from wishlist
    @DeleteMapping("/remove/{wishlistId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable Long wishlistId) {
        wishlistService.removeProductFromWishlist(wishlistId);
        return ResponseEntity.noContent().build();
    }
}
