package com.ness.app.domain.wraper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ness.app.domain.model.Area;
import com.ness.app.domain.model.Knowledge;

public class KnowledgeWithSelection {
	
	public static List<KnowledgeWithSelection> from(List<Knowledge> knowledges) {
		List<KnowledgeWithSelection> result = new ArrayList<>();
		for (Knowledge knowledge : knowledges) {
			KnowledgeWithSelection kws = new KnowledgeWithSelection();
			kws.setKnowledgeId(knowledge.getKnowledgeId());
			kws.setTitle(knowledge.getTitle());
			kws.setArea(knowledge.getArea());
			kws.setSelected(false);
			result.add(kws);
		}
		return result;
	}
	
	public static Comparator<KnowledgeWithSelection> getComparatorWithoutSelectedField() {
		return Comparator
				.comparing(KnowledgeWithSelection::getKnowledgeId)
				.thenComparing(KnowledgeWithSelection::getTitle);	
	}
	
	private Long knowledgeId;
	private String title;
	private Area area;
	private Boolean selected;
	
	public KnowledgeWithSelection() { }
	
	public KnowledgeWithSelection(String title, Area area, Boolean selected) {
		this.selected = selected;
	}
	
	public KnowledgeWithSelection(Knowledge knowledge, Boolean selected) {
		this.knowledgeId = knowledge.getKnowledgeId();
		this.title = knowledge.getTitle();
		this.area = knowledge.getArea();
		this.selected = selected;
	}

	public Long getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Long knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((knowledgeId == null) ? 0 : knowledgeId.hashCode());
		result = prime * result + ((selected == null) ? 0 : selected.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KnowledgeWithSelection other = (KnowledgeWithSelection) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (knowledgeId == null) {
			if (other.knowledgeId != null)
				return false;
		} else if (!knowledgeId.equals(other.knowledgeId))
			return false;
		if (selected == null) {
			if (other.selected != null)
				return false;
		} else if (!selected.equals(other.selected))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
