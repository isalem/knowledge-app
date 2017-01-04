package com.skills.web.dto;

import java.util.List;

public class AutocompleteDto {
	private String text;
	private List<AutocompleteChildrenDto> children;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<AutocompleteChildrenDto> getChildren() {
		return children;
	}

	public void setChildren(List<AutocompleteChildrenDto> children) {
		this.children = children;
	}

	public static class AutocompleteChildrenDto {
		private Long id;
		private String text;
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getText() {
			return text;
		}
		
		public void setText(String text) {
			this.text = text;
		}
	}
}
