package com.ness.knowledges.persistent.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "areas")
public class AreaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long areaId;
	
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<KnowledgeEntity> knowledges;
	
	public AreaEntity() { }
	
	public AreaEntity(String title) {
		this(title, null);
	}
	public AreaEntity(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return areaId;
	}

	public void setId(Long id) {
		this.areaId = id;
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

	public Collection<KnowledgeEntity> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(Collection<KnowledgeEntity> knowledges) {
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
		AreaEntity other = (AreaEntity) obj;
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
