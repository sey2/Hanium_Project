package com.fishing.demo.api.service;

import com.fishing.demo.api.dto.request.CommentRequestDto;
import com.fishing.demo.api.dto.response.CommentResponseDto;

public interface CommentService {

    // Define the method to add a comment to a board
    CommentResponseDto addCommentToBoard(Long boardId, CommentRequestDto commentRequestDto);

    // Define the method to get a comment by its ID
    CommentResponseDto getCommentById(Long id);

    // You can add other methods related to comment service here

}
