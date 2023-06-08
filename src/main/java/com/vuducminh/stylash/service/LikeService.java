package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Like;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.user.User;
import org.springframework.data.jpa.repository.Query;

public interface LikeService {
    void unlikeProduct(User user, Product product);
    boolean isProductLikedByUser(User user, Product product);
    Like likeProduct(Like like);
}
