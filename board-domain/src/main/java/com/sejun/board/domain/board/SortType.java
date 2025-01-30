package com.sejun.board.domain.board;

public enum SortType {
    ASC,
    DESC;

    public boolean isAscending() {
        return this == ASC;
    }

}
