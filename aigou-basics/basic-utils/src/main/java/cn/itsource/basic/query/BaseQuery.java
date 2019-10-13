package cn.itsource.basic.query;

public class BaseQuery {
    private Integer page;//pageNum
    private Integer rows;//pageSize
    private String keyword;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "page=" + page +
                ", size=" + rows +
                ", keyword='" + keyword + '\'' +
                '}';
    }

    public BaseQuery(Integer page, Integer size, String keyword) {
        this.page = page;
        this.rows = size;
        this.keyword = keyword;
    }

    public BaseQuery() {
    }
}
