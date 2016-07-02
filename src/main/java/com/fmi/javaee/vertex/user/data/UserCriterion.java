package com.fmi.javaee.vertex.user.data;

import com.fmi.javaee.vertex.user.Gender;

public interface UserCriterion {

	UserCriterion id(Long id);

	UserCriterion email(String email);

	UserCriterion jobTitle(String jobTitle);

	UserCriterion isGod(Boolean isGod);

	UserCriterion gender(Gender gender);
}
