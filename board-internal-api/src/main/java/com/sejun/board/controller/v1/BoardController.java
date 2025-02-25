package com.sejun.board.controller.v1;

import com.sejun.board.controller.v1.request.BoardCreateRequest;
import com.sejun.board.controller.v1.request.ModifyBoardRequest;
import com.sejun.board.controller.v1.response.BoardResponse;
import com.sejun.board.controller.v1.response.DefaultIdResponse;
import com.sejun.board.domain.board.*;
import com.sejun.board.support.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardservice;
    
    @GetMapping("/v1/boards")
    public ApiResponse<List<BoardResponse>> boards(
            @RequestParam(name = "cursor", defaultValue = "0", required = false) Long cursor,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "sortType", defaultValue = "DESC") SortType sortType,
            @RequestParam(name = "sortField", defaultValue = "ID") SortField sortField
    ) {
        List<Board> boards = boardservice.find(new Cursor(cursor, limit, sortType, sortField));
        return ApiResponse.success(BoardResponse.of(boards));
    }
    
    @PostMapping("/v1/boards")
    public ApiResponse<Long> createBoard(@RequestBody @Valid BoardCreateRequest request) {
        return ApiResponse.success(boardservice.createBoard(
                Board.builder()
                        .title(request.title())
                        .content(request.content())
                        .build()));
    }
    
    @GetMapping("/v1/boards/{boardId}")
    public ApiResponse<BoardResponse> getBoard(@PathVariable("boardId") Long boardId) {
        Board board = boardservice.find(boardId);
        return ApiResponse.success(BoardResponse.of(board));
    }
    
    @PutMapping("/v1/boards/{boardId}")
    public ApiResponse<DefaultIdResponse> modifyBoard(
            @PathVariable("boardId") long boardId,
            @RequestBody @Valid ModifyBoardRequest request) {
        Board modifyBoard = boardservice.modify(boardId, request.toContent());
        return ApiResponse.success(DefaultIdResponse.of(modifyBoard));
    }
}
