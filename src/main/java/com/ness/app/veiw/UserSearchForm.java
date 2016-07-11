package com.ness.app.veiw;

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
		String[] rawRequestedKnowledges = searchRequest.split(",");
		return Arrays.stream(rawRequestedKnowledges)
			.map(k -> k.trim())
			.collect(Collectors.toSet());
	}
}
