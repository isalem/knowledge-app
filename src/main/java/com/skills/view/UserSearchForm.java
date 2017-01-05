package com.skills.view;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSearchForm {
    private String searchRequest;

    public String getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(String searchRequest) {
        this.searchRequest = searchRequest;
    }

    public Set<String> getParsedSearchRequest() {
        String[] rawRequestedSkills = searchRequest.split(",");
        return Arrays.stream(rawRequestedSkills)
                .map(s -> s.trim())
                .collect(Collectors.toSet());
    }
}
