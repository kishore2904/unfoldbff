package com.unfold.unfoldbff.service.impl;

import com.unfold.unfoldbff.mapper.ProductMapper;
import com.unfold.unfoldbff.mapper.ProductVariantMapper;
import com.unfold.unfoldbff.model.dto.ProductDto;
import com.unfold.unfoldbff.model.dto.ProductImageDto;
import com.unfold.unfoldbff.model.dto.ProductVariantDto;
import com.unfold.unfoldbff.model.entity.Category;
import com.unfold.unfoldbff.model.entity.Product;
import com.unfold.unfoldbff.model.entity.ProductImage;
import com.unfold.unfoldbff.model.entity.ProductVariant;
import com.unfold.unfoldbff.repository.CategoryRepository;
import com.unfold.unfoldbff.repository.ProductImageRepository;
import com.unfold.unfoldbff.repository.ProductRepository;
import com.unfold.unfoldbff.repository.ProductVariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final ProductVariantMapper productVariantMapper;
    private final ProductVariantRepository productVariantRepository;
    private final ProductImageRepository productImageRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                              ProductMapper productMapper, ProductVariantMapper productVariantMapper,
                              ProductVariantRepository productVariantRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.productVariantMapper = productVariantMapper;
        this.productVariantRepository = productVariantRepository;
        this.productImageRepository = productImageRepository;
    }

    public ProductDto getProductBasedOnCategoryIdAndProductId(Integer categoryId, Integer productId) {
        Product product = productRepository.findProductByCategoryIdAndProductId(categoryId, productId);
        List<ProductVariant> variants = productVariantRepository.findByProductId(productId);
        ProductDto productDto = productMapper.convertToProductDto(product);
        List<ProductVariantDto> variantDtos = productVariantMapper.convertToProductVariantDtoList(variants);
        productDto.setProductVariantDtos(variantDtos); // Add variants to the product DTO
        return productDto;
    }

    public void createProductsUnderCategory(Integer categoryId, ProductDto productDtos) {
        Category category = validateCategory(categoryId);

        Product product = new Product();
        product.setCategory(category);
        product.setCategoryId(categoryId);
        product.setProductName(productDtos.getProductName());
        product.setProductDescription(productDtos.getProductDescription());
        product.setImageUrl("https://firebasestorage.googleapis.com/v0/b/unfold-fit.firebasestorage.app/o/products%2Funfold.png?alt=media&token=1c1057b3-5185-45ee-8060-05713a1e82a0");
        product.setPrice(productDtos.getPrice());
        product.setStockQuantity(productDtos.getStockQuantity());
        product = productRepository.save(product);

        if (product.getProductId() != null && !productDtos.getProductVariantDtos().isEmpty()) {
            for (ProductVariantDto productVariantDto : productDtos.getProductVariantDtos()) {
                ProductVariant productVariant = new ProductVariant();
                productVariant.setProduct(product);
                productVariant.setProductId(Long.valueOf(product.getProductId()));
                productVariant.setColorId(productVariantDto.getColorId());
                productVariant.setSizeId(productVariantDto.getSizeId());
                productVariant.setPrice(productVariantDto.getPrice());
                productVariant.setStockQuantity(productVariantDto.getStockQuantity());
                productVariant = productVariantRepository.save(productVariant);
                if (!productVariantDto.getProductImageDtos().isEmpty()) {
                    for (ProductImageDto productImageDto : productVariantDto.getProductImageDtos()) {
                        ProductImage productImage = new ProductImage();
                        productImage.setProduct(product);
                        productImage.setProductVariant(productVariant);
                        productImage.setImageUrl(productImageDto.getImageUrl());
                        productImageRepository.save(productImage);
                    }
                }


            }
        }
    }

    public void updateProductUnderCategory(Integer categoryId, ProductDto productDto) {
        Category category = validateCategory(categoryId);
        if (category != null) {
            Product product = productMapper.convertToProduct(productDto);
            productRepository.updateProductBasedOnCategoryAndProductId(categoryId, product.getProductId(),
                    product.getProductName(), product.getProductDescription(), product.getPrice(),
                    product.getStockQuantity(), product.getImageUrl());
            saveVariantsForProduct(product);
        }
    }

    public void deleteProductByCategoryAndProductId(Integer categoryId, Integer productId) {
        productRepository.deleteProductByCategoryAndProductId(categoryId, productId);
        productVariantRepository.deleteByProductId(productId); // Ensure variants are deleted as well
    }

    private void saveVariantsForProduct(Product product) {
        if (product.getVariants() != null) {
            List<ProductVariant> variants = product.getVariants();
            productVariantRepository.saveAll(variants); // Save the variants for the product
        }
    }

    private void saveVariantsForProducts(List<Product> productList) {
        for (Product product : productList) {
            saveVariantsForProduct(product);
        }
    }

    private Category validateCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category Doesn't exist")
        );
        return category;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productMapper.convertToProductDtoList(productList);
    }

    public List<ProductDto> getProductBasedOnCategoryId(Integer categoryId) {
        List<Product> productList = productRepository.findProductByCategoryId(categoryId);
        return productMapper.convertToProductDtoList(productList);
    }

    public ProductDto getProductDetails(Integer productId) {
        Product product = productRepository.findByProductId(productId);
        List<ProductVariant> variants = productVariantRepository.findByProductId(productId);
        ProductDto productDto = productMapper.convertToProductDto(product);
        List<ProductVariantDto> variantDtos = productVariantMapper.convertToProductVariantDtoList(variants);
        productDto.setProductVariantDtos(variantDtos); // Set variants for the product
        return productDto;
    }
}
