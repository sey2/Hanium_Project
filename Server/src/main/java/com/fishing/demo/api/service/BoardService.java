package com.fishing.demo.api.service;

import com.fishing.demo.api.dto.request.BoardRequestDto;
import com.fishing.demo.api.dto.response.BoardDto;

public interface BoardService {

    // Define the method to create a new board
    BoardDto createBoard(BoardRequestDto boardRequestDto);

    // Define the method to get a board by its ID
    BoardDto getBoardById(Long id);

    // You can add other methods related to board service here

}
