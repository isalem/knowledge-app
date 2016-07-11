package com.ness.app.knowledge;

import java.util.List;

import com.ness.app.area.Area;

public class EditAreasAndKnowledgesForm {

	private List<Area> areas;
	private List<Knowledge> knowledges;
	
	public EditAreasAndKnowledgesForm() {
		
	}
	
	public EditAreasAndKnowledgesForm(List<Area> areas, List<Knowledge> knowledges) {
		this.areas = areas;
		this.knowledges = knowledges;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}
}
