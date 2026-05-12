package com.epw.skillswap.service;

import com.epw.skillswap.dto.ReviewDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ReviewDTO createReview(ReviewDTO dto);

    ReviewDTO getReviewById(UUID reviewId);

    List<ReviewDTO> getAllReviews();

    void deleteReview(UUID reviewId);
}