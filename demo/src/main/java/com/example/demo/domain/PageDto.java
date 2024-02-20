package com.example.demo.domain;

import lombok.Data;

@Data
public class PageDto {

    private int page;
    private int pageSize;
    private int totalCount;

    private int startPage;
    private int endPage;
    private int totalPage;
    private int offset;

    private boolean prevArrow; // < 
    private boolean startArrow; // <<
    private boolean nextArrow; // >
    private boolean endArrow; // >>

    public PageDto(int page, int pageSize, int totalCount) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        totalPage = (totalCount - 1) / pageSize + 1;
        startPage = (this.page - 1) / 10 * 10 + 1;
        endPage = Math.min(startPage + 9, totalPage);
        offset = (page - 1) * 10;
        
        prevArrow = startPage != 1;
        startArrow = startPage != 1;
        nextArrow = endPage != totalPage;
        endArrow = endPage != totalPage;
    }
}
