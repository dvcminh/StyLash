package com.vuducminh.stylash.controller;

import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.user.User;
import com.vuducminh.stylash.service.LikeService;
import com.vuducminh.stylash.service.ProductService;
import com.vuducminh.stylash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public LikeController(LikeService likeService, ProductService productService, UserService userService) {
        this.likeService = likeService;
        this.productService = productService;
        this.userService = userService;
    }


    @DeleteMapping("/{productId}")
    public void unlikeProduct(@PathVariable Integer productId, @AuthenticationPrincipal User user) {
        Product product = productService.viewById(productId);
        likeService.unlikeProduct(user, product);
    }

    @GetMapping("/{productId}/check")
    public boolean isProductLikedByUser(@PathVariable Integer productId, @AuthenticationPrincipal User user) {
        Product product = productService.viewById(productId);
        return likeService.isProductLikedByUser(user, product);
    }
}
