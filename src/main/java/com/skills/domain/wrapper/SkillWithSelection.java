package com.skills.domain.wrapper;

import com.skills.domain.model.Area;
import com.skills.domain.model.Skill;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SkillWithSelection {

    public static List<SkillWithSelection> from(List<Skill> skills) {
        List<SkillWithSelection> result = new ArrayList<>();
        for (Skill skill : skills) {
            SkillWithSelection kws = new SkillWithSelection();
            kws.setSkillId(skill.getSkillId());
            kws.setTitle(skill.getTitle());
            kws.setArea(skill.getArea());
            kws.setSelected(false);
            result.add(kws);
        }
        return result;
    }

    public static Comparator<SkillWithSelection> getComparatorWithoutSelectedField() {
        return Comparator
                .comparing(SkillWithSelection::getSkillId)
                .thenComparing(SkillWithSelection::getTitle);
    }

    private Long skillId;
    private String title;
    private Area area;
    private Boolean selected;

    public SkillWithSelection() {
    }

    public SkillWithSelection(String title, Area area, Boolean selected) {
        this.selected = selected;
    }

    public SkillWithSelection(Skill skill, Boolean selected) {
        this.skillId = skill.getSkillId();
        this.title = skill.getTitle();
        this.area = skill.getArea();
        this.selected = selected;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
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
        result = prime * result + ((skillId == null) ? 0 : skillId.hashCode());
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
        SkillWithSelection other = (SkillWithSelection) obj;
        if (area == null) {
            if (other.area != null)
                return false;
        } else if (!area.equals(other.area))
            return false;
        if (skillId == null) {
            if (other.skillId != null)
                return false;
        } else if (!skillId.equals(other.skillId))
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
