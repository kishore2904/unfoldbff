package com.unfold.unfoldbff.controller;

import com.unfold.unfoldbff.model.dto.CategoryDto;
import com.unfold.unfoldbff.service.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rest/unfold")
public class CategoriesController {

    private final CategoryServiceImpl categoryServiceImpl;

    public CategoriesController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/allCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryServiceImpl.findAllCategories());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/categories/{id}")
    public ResponseEntity<List<CategoryDto>> findCategoryAndProductsByCategoryId(@PathVariable Integer id){
        return ResponseEntity.ok(categoryServiceImpl.findCategoryAndProductsByCategoryId(id));
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/categories")
    @PreAuthorize("hasRole('Admin')")
    ResponseEntity<Void> saveCategories(@RequestBody CategoryDto categoryDto){
        categoryServiceImpl.createCategories(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/categories/{categoryId}")
    ResponseEntity<Void> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto){

        categoryServiceImpl.updateCategory(categoryId,categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/categories/{deleteId}")
    ResponseEntity<Void> deleteCategoryWithProduct(@PathVariable Integer categoryId){

        categoryServiceImpl.deleteCategoryWithProduct(categoryId);
        return ResponseEntity.noContent().build();

    }
}
