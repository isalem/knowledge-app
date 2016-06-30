package com.ness.knowledges.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Knowledge {

	public static Knowledge getEmtyKnowledgeForArea(Area area) {
		Knowledge instanse = new Knowledge();
		instanse.setArea(area);
		return instanse;
	}
	
	public static Knowledge getEmtyKnowledge() {
		return new Knowledge();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "knowledge_id")
	private Long knowledgeId;
	
	@NotNull
	@Length(max = 100)
	@Column(nullable = false, length = 100)
	private String title;
	
	@Length(max = 500)
	@Column(length = 500)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "area_id", referencedColumnName = "area_id")
	private Area area;
	
	public Knowledge(String title, String description, Area area) {
		this.title = title;
		this.description = description;
		this.area = area;
	}

	public Knowledge(String title, String description) {
		this(title, description, null);
	}
	
	public Knowledge() { }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Knowledge other = (Knowledge) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
