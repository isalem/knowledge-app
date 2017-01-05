package com.skills.view;

import com.skills.domain.wrapper.SkillWithSelection;

import java.util.List;

public class SkillSettingsForm {
    private List<SkillWithSelection> skills;

    public SkillSettingsForm() {
    }

    public SkillSettingsForm(List<SkillWithSelection> skills) {
        this.skills = skills;
    }

    public List<SkillWithSelection> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillWithSelection> skills) {
        this.skills = skills;
    }
}
