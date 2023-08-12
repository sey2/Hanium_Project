package com.fishing.demo.api.controller;

import com.fishing.demo.api.dto.request.CommentRequestDto;
import com.fishing.demo.api.dto.response.CommentResponseDto;
import com.fishing.demo.api.service.CommentService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "Add a comment to a board")
    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentResponseDto> addCommentToBoard(
            @PathVariable Long boardId,
            @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.addCommentToBoard(boardId, commentRequestDto);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    @ApiOperation(value = "Get a comment by ID")
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long id) {
        CommentResponseDto commentResponseDto = commentService.getCommentById(id);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    // Add other comment-related endpoints as needed...
}