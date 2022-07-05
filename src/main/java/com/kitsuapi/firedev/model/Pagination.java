package com.kitsuapi.firedev.model;

import lombok.Getter;

@Getter
public class Pagination {
    Integer first = 0;
    Integer next;
    Integer previous;

    public Pagination(Integer page) {
        this.next = page + 18;
        this.previous = page - 18;
    }
}
