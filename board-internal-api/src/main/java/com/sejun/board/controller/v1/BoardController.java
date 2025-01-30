package com.sejun.board.controller.v1;

import com.sejun.board.controller.v1.request.BoardCreateRequest;
import com.sejun.board.domain.board.*;
import com.sejun.board.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;
    @GetMapping("/v1/boards")
    public ApiResponse<List<Board>> boards(
        @RequestParam(name = "cursor", defaultValue = "0", required = false) Long cursor,
        @RequestParam(name = "limit", defaultValue = "10") int limit,
        @RequestParam(name = "sortType", defaultValue = "DESC") SortType sortType,
        @RequestParam(name = "sortField", defaultValue = "ID") SortField sortField
    ) {
        return ApiResponse.success(boardservice.find(new Cursor(cursor, limit, sortType, sortField)));
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
