package com.sejun.board.controller.v1;

import com.sejun.board.controller.v1.request.BoardCreateRequest;
import com.sejun.board.domain.board.Board;
import com.sejun.board.domain.board.BoardService;
import com.sejun.board.support.ApiResponse;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;
    @GetMapping("/v1/boards")
    public ApiResponse<List<Board>> boards(
        @RequestParam(required = false, defaultValue = "0") Long offset,
        @RequestParam(defaultValue = "10") int limit
    ) {
        return ApiResponse.success(boardservice.find(offset, limit));
    }

    @PostMapping("/v1/boards")
    public ApiResponse<Long> createBoard(@RequestBody BoardCreateRequest request) {
        return ApiResponse.success(boardservice.createBoard(
                Board.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .build()));
    }
}
