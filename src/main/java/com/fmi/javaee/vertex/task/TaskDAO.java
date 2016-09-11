package com.fmi.javaee.vertex.task;

import java.util.List;

import com.fmi.javaee.vertex.user.UserEntity;

public interface TaskDAO {

	TaskEntity getTaskById(String id);

	TaskEntity createTask(TaskEntity task);

	TaskEntity updateTask(TaskEntity task);

	List<TaskEntity> getTasksByAssignee(UserEntity asignee);

	List<TaskEntity> getTasksByCreator(UserEntity creator);

}
