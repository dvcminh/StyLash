package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Like;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.repository.LikeRepository;
import com.vuducminh.stylash.user.User;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public void unlikeProduct(User user, Product product) {
        likeRepository.deleteByUserAndProduct(user, product);
    }

    @Override
    public boolean isProductLikedByUser(User user, Product product) {
        return likeRepository.existsByUserAndProduct(user, product);
    }

    @Override
    public Like likeProduct(Like like) {
        return likeRepository.save(like);
    }
}

