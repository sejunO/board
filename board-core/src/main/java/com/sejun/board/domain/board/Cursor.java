package com.sejun.board.domain.board;

public class Cursor {
    private Long offset = 0L;
    private int limit;
    private SortType sortType;

    private SortField sortField;

    public Cursor(Long offset, int limit, SortType sortType, SortField sortField) {
        this.offset = offset != null ? offset : 0L;
        this.limit = limit;
        this.sortType = sortType;
        this.sortField = sortField;
    }

    public Long getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public SortType getSortType() {
        return sortType;
    }

    public SortField getSortField() {
        return sortField;
    }
}
