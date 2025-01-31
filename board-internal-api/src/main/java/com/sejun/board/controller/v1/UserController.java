package com.sejun.board.controller.v1;

import com.sejun.board.controller.v1.request.UserSignUpRequest;
import com.sejun.board.domain.User.UserService;
import com.sejun.board.support.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/v1/users/sign-up")
    public ApiResponse<Long> singUp(@RequestBody UserSignUpRequest request) {
        return ApiResponse.success(userService.create(request.toSignUp()));
    }
}
