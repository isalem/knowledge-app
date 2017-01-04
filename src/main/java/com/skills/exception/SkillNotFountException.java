package com.skills.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Skill not found.")
public class SkillNotFountException extends SkillAppException {

	private static final long serialVersionUID = -5558726110036986986L;


}
