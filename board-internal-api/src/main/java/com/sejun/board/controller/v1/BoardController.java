package com.sejun.board.controller.v1;

import com.sejun.board.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @GetMapping("/v1/boards")
    public ApiResponse<Object> boards(
        @RequestParam Long offset,
        @RequestParam Long limit
    ) {
        return null;
    }
}
