package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.exception.NotFoundException;
import com.unfold.unfoldbff.model.dto.WishlistDto;
import com.unfold.unfoldbff.model.entity.Product;
import com.unfold.unfoldbff.model.entity.Users;
import com.unfold.unfoldbff.model.entity.Wishlist;
import com.unfold.unfoldbff.mapper.WishlistMapper;
import com.unfold.unfoldbff.repository.WishlistRepository;
import com.unfold.unfoldbff.repository.ProductRepository;
import com.unfold.unfoldbff.repository.UsersRepository;
import com.unfold.unfoldbff.utils.ErrorMessage;
import com.unfold.unfoldbff.utils.WishlistStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl {

    private final WishlistRepository wishlistRepository;
    private final UsersRepository userRepository;
    private final ProductRepository productRepository;
    private final WishlistMapper wishlistMapper;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UsersRepository userRepository, ProductRepository productRepository, WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.wishlistMapper = wishlistMapper;
    }

    // Add product to wishlist

    public WishlistDto addProductToWishlist(Long userId, Long productId) {
        Optional<Users> user = userRepository.findById(userId);
        Optional<Product> product = productRepository.findById(Math.toIntExact(productId));

        if (user.isEmpty() || product.isEmpty()) {
            throw new RuntimeException("User or Product not found");
        }

        // Check if the product is already in the user's wishlist
        Optional<Wishlist> existingWishlistItem = wishlistRepository.findByUserAndProduct(user.get(), product.get());
        if (existingWishlistItem.isPresent()) {
            throw new NotFoundException(ErrorMessage.USER_NAME_ALREADY_EXIST);
        }

        // Create new wishlist item
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user.get());
        wishlist.setProduct(product.get());
        wishlist.setStatus(WishlistStatus.active);

        // Save and return the added wishlist item
        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return wishlistMapper.convertToWishlistDto(savedWishlist);
    }

    // Get wishlist for a user
    public List<WishlistDto> getWishlistByUserId(Long userId) {
        List<Wishlist> wishlist = wishlistRepository.findWishlistAndUserAndProductByUserId(userId);
        return wishlistMapper.convertToWishlistDto(wishlist);
    }

    // Edit wishlist item status

    public WishlistDto editWishlistItemStatus(Long wishlistId, String status) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
        if (wishlistOptional.isEmpty()) {
            throw new RuntimeException("Wishlist item not found");
        }

        Wishlist wishlist = wishlistOptional.get();
        wishlist.setStatus(WishlistStatus.valueOf(status.toUpperCase())); // Assuming valid enum value
        Wishlist updatedWishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.convertToWishlistDto(updatedWishlist);
    }

    // Remove product from wishlist

    public void removeProductFromWishlist(Long wishlistId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(wishlistId);
        if (wishlistOptional.isEmpty()) {
            throw new RuntimeException("Wishlist item not found");
        }

        wishlistRepository.delete(wishlistOptional.get());
    }
}
