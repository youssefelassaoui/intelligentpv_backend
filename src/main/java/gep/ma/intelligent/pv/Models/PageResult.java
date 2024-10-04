package gep.ma.intelligent.pv.Models;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResult<T> {
    // Getters and setters
    private List<T> data;  // The actual data for the current page
    private String pagingState;  // The paging state for the next page, or null if there are no more pages

    // Constructor
    public PageResult(List<T> data, String pagingState) {
        this.data = data;
        this.pagingState = pagingState;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setPagingState(String pagingState) {
        this.pagingState = pagingState;
    }
}

