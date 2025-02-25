package com.sejun.board.controller.v1.request;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneNotBlankValidator implements ConstraintValidator<AtLeastOneNotBlank, ModifyBoardRequest> {
    @Override
    public boolean isValid(ModifyBoardRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true; // 필요에 따라 null 체크 로직을 추가하세요.
        }
        return (request.title() != null && !request.title().isBlank()) ||
                (request.content() != null && !request.content().isBlank());
    }
}
