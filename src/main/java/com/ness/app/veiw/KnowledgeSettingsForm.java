package com.ness.app.veiw;

import java.util.List;

import com.ness.app.domain.wrapper.KnowledgeWithSelection;

public class KnowledgeSettingsForm {
	private List<KnowledgeWithSelection> knowledges;

	public KnowledgeSettingsForm() { }
	
	public KnowledgeSettingsForm(List<KnowledgeWithSelection> knowledges) {
		this.knowledges = knowledges;
	}

	public List<KnowledgeWithSelection> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<KnowledgeWithSelection> knowledges) {
		this.knowledges = knowledges;
	}
}
