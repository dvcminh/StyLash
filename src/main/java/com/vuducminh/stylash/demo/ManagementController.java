package com.vuducminh.stylash.demo;

import com.vuducminh.stylash.controller.dto.CategoryDto;
import com.vuducminh.stylash.controller.dto.CreateProductRequest;
import com.vuducminh.stylash.controller.dto.ProductDto;
import com.vuducminh.stylash.controller.dto.UserDto;
import com.vuducminh.stylash.mapper.CategoryMapper;
import com.vuducminh.stylash.mapper.ProductMapper;
import com.vuducminh.stylash.mapper.UserMapper;
import com.vuducminh.stylash.model.Category;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.service.CategoryService;
import com.vuducminh.stylash.service.ProductService;
import com.vuducminh.stylash.service.UserService;
import com.vuducminh.stylash.user.User;
import com.vuducminh.stylash.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
@Tag(name = "Management")
public class ManagementController {

    private final UserService userService;
    private final UserMapper userMapper;

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )

    @GetMapping("/getUsers")
    public List<UserDto> getUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/me")
    public UserDto getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return userMapper.toUserDto(userService.validateAndGetUserByUsername(currentUser.getUsername()));
    }

    @GetMapping("/getCategories")
    public List<CategoryDto> getCategories() {
        return categoryService.getAllCategories().stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ProductDto createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        Product product = productMapper.toProduct(createProductRequest);
        return productMapper.toProductDto(productService.save(product));
    }

    @PostMapping
    public String post() {
        return "POST:: management controller";
    }
    @PutMapping
    public String put() {
        return "PUT:: management controller";
    }
    @DeleteMapping
    public String delete() {
        return "DELETE:: management controller";
    }
}
