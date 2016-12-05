package com.wfj.dto;

/**
 * page
 *
 * @author kongqf
 * @create 2016-12-02
 */
public class PageBase {
    /**
     * 当前页
     */
    private Integer currentPage = 1;

    /**
     * 单页行数 默认20
     */
    private Integer pageSize = 10;
    private Integer start;
    private Integer limit;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        this.start = 0;
        if (this.currentPage > 1 && this.pageSize > 0) {
            this.start = (this.currentPage - 1) * this.pageSize;
        }
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        if (this.pageSize != null)
            this.limit = this.pageSize;
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
