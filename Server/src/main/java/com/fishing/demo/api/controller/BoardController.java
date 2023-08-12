package com.fishing.demo.api.controller;

import com.fishing.demo.api.dto.request.BoardRequestDto;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fishing.demo.api.dto.response.BoardDto;
import com.fishing.demo.api.service.BoardService;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "Create a new board")
    @PostMapping("/boards")
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        BoardDto boardResponseDto = boardService.createBoard(boardRequestDto);
        return ResponseEntity.ok().body(boardResponseDto);
    }

    @ApiOperation(value = "Get a board by ID")
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable Long id) {
        BoardDto boardResponseDto = boardService.getBoardById(id);
        return ResponseEntity.ok().body(boardResponseDto);
    }

}