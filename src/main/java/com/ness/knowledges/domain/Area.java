package com.ness.knowledges.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Area {
	
	public static Area getEmptyArea() {
		return new Area();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_id")
	private Long areaId;
	
	@NotNull
	@Length(max = 100)
	@Column(nullable = false, length = 100)
	private String title;
	
	@Length(max = 500)
	@Column(length = 500)
	private String description;
	
	@OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Knowledge> knowledges;
	
	public Area(String title, String description, Set<Knowledge> knowledges) {
		this.title = title;
		this.description = description;
		this.knowledges = knowledges;
	}
	
	public Area(String title) {
		this(title, null, new HashSet<>());
	}
	
	protected Area() { }

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

	public Set<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(Set<Knowledge> knowledges) {
		this.knowledges = knowledges;
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
		Area other = (Area) obj;
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
