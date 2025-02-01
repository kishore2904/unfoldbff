package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.exception.NotFoundException;
import com.unfold.unfoldbff.mapper.CartMapper;
import com.unfold.unfoldbff.model.dto.CartDto;
import com.unfold.unfoldbff.model.entity.Cart;
import com.unfold.unfoldbff.model.entity.Product;
import com.unfold.unfoldbff.model.entity.Users;
import com.unfold.unfoldbff.repository.CartRepository;
import com.unfold.unfoldbff.repository.ProductRepository;
import com.unfold.unfoldbff.repository.UsersRepository;
import com.unfold.unfoldbff.utils.ErrorMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final UsersRepository usersRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository
            , CartMapper cartMapper, UsersRepository usersRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
        this.usersRepository = usersRepository;
    }

    public void addProductsOrModifyQuantityOfProductToCart(CartDto cartDto) {
        Optional<Users> users = usersRepository.findById(Long.valueOf(cartDto.getUserId()));
        if (users.isPresent()) {
            Product product = productRepository.findByProductId(cartDto.getProductId());

            if (product != null &&
                    (cartDto.getQuantity() < product.getStockQuantity())) {

                Cart cartDetails = cartRepository.findByUserIdAndProductId(cartDto.getUserId(), cartDto.getProductId());

                if (cartDetails != null) {
                    Integer addingQuantityToExisting = cartDto.getQuantity() + cartDetails.getQuantity();
                    cartDetails.setQuantity(cartDto.getQuantity());
                    cartRepository.save(cartDetails);
                } else {
                    Cart cart = cartMapper.convertToCart(cartDto);
                    cart.setCreatedAt(LocalDateTime.now());
                    cartRepository.save(cart);
                }

            } else {
                throw new RuntimeException("Product doesn't exists");
            }
        } else {
            throw new NotFoundException(ErrorMessage.DATA_NOT_FOUND);
        }

    }

    public List<CartDto> getAllCartItemsByUserId(Integer userId) {

        List<Cart> carts = cartRepository.findByUserId(userId);
        return cartMapper.convertToCartDto(carts);

    }

    public void delete(Integer cartId) {

        cartRepository.deleteById(cartId);

    }
}
