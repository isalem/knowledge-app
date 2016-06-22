package com.ness.knowledges.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "knowledges")
public class Knowledge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@ManyToOne
	private Knowledge parent;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	private Collection<Knowledge> knowledges;
	
	public Knowledge() { }
	
	public Knowledge(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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

	public Knowledge getParent() {
		return parent;
	}

	public void setParent(Knowledge parent) {
		this.parent = parent;
	}

	public Collection<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(Collection<Knowledge> knowledges) {
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
