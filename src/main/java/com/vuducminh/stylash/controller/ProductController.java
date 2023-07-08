package com.vuducminh.stylash.controller;

import com.vuducminh.stylash.model.Category;
import com.vuducminh.stylash.model.Like;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.service.CategoryService;
import com.vuducminh.stylash.service.LikeService;
import com.vuducminh.stylash.service.ProductService;
import com.vuducminh.stylash.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final LikeService likeService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestParam("name") String name,
                                                 @RequestParam("imageFile") MultipartFile imageFile,
                                                 @RequestParam("price") BigDecimal price,
                                                 @RequestParam("description") String description,
                                                 @RequestParam("selectedCategory") String category) {
        try {
            Category createdCategory = categoryService.getCategoryByName(category);
            Product createdProduct = productService.createProduct(name, imageFile, description, price, createdCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getProductCount() {
        return ResponseEntity.ok(productService.viewAll().size());
    }

    @GetMapping("/top-liked-products")
    public ResponseEntity<List<Product>> getTopLikedProducts() {
        List<Product> products = productService.getTopLikedProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "name", required = false) String name,
                                                        @RequestParam(value = "sort", required = false) String sort) {
        List<Product> products;
        if(sort != null){
            if (sort.equals("asc")) {
                products = productService.getProductAsc();
            }
            else {
                products = productService.getProductDesc();
            }
        }
        else if(name != null) {
            products = productService.findbyNameContaining(name);
        }
        else {
            products = productService.viewAll();
        }

        return ResponseEntity.ok(products);
    }


    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        List<Product> products = productService.findProductsByCategoryName(categoryName);
        return ResponseEntity.ok(products);
    }

//    @GetMapping("/countLike/{productId}")
//    public ResponseEntity<List<Like>> countProductLike(@PathVariable Integer productId) {
//        Product product = productService.viewById(productId);
//        List<Like> listLike= likeService.findLikeByProduct(product);
//        return ResponseEntity.ok(listLike);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Product product = productService.viewById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/checkLikedProduct")
    public ResponseEntity<Boolean> likedProduct(@RequestParam("productId") Integer productId, @AuthenticationPrincipal User user) {
        Product product = productService.viewById(productId);
        if (!likeService.isProductLikedByUser(user, product)) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Boolean> likeProduct(@PathVariable Integer productId, @AuthenticationPrincipal User user) {
        Product product = productService.viewById(productId);
        Like like = new Like(user, product);
        // Kiểm tra xem người dùng đã thích sản phẩm chưa
        if (!likeService.isProductLikedByUser(user, product)) {
            likeService.likeProduct(like);
            System.err.println("Thích sản phẩm thành công.");
            return ResponseEntity.ok(true);
        } else {

            likeService.unlikeProduct(user, product);
            System.err.println("Người dùng đã thích sản phẩm này.");
            return ResponseEntity.ok(false);
        }
    }

//    @GetMapping("/{categoryId}")
//    public List<Product> getAllProductByCategory(@RequestBody Integer id) {
//        return productService.getAllCategories();
//    }
}
