package com.unfold.unfoldbff.mapper;

import com.unfold.unfoldbff.model.dto.WishlistDto;
import com.unfold.unfoldbff.model.entity.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistMapper {

    // Convert WishlistDto to Wishlist (Handling userId and productId)
    @Mapping(source = "userId", target = "user.user_id")  // Mapping userId from DTO to user.user_id (from Users entity)
    @Mapping(source = "productId", target = "product.productId")  // Mapping productId from DTO to product.productId (from Product entity)
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dateAdded", target = "dateAdded")
    Wishlist convertToWishlist(WishlistDto wishlistDTO);

    // Convert Wishlist to WishlistDto (Handling userId and productId)
    @Mapping(source = "user.user_id", target = "userId")  // Mapping user.user_id from Wishlist to userId in DTO
    @Mapping(source = "product.productId", target = "productId")  // Mapping product.productId from Wishlist to productId in DTO
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dateAdded", target = "dateAdded")
    WishlistDto convertToWishlistDto(Wishlist wishlist);

    // Convert List<Wishlist> to List<WishlistDto>
    List<WishlistDto> convertToWishlistDto(List<Wishlist> wishlists);
}
