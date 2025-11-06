package com.MyProject.InsuranceApp.wrappers;

import com.MyProject.InsuranceApp.dto.SearchResponseDTO;

import java.util.List;

public class SearchResponseWrapperDTO {
    private int count;
    private List<SearchResponseDTO> results;

    public SearchResponseWrapperDTO(List<SearchResponseDTO> results) {
        this.results = results;
        this.count = results.size();
    }

    public int getCount() {
        return count;
    }

    public List<SearchResponseDTO> getResults() {
        return results;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setResults(List<SearchResponseDTO> results) {
        this.results = results;
    }
}
