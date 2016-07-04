package com.fmi.javaee.vertex.task.data;

import java.util.List;

import com.fmi.javaee.vertex.task.Priority;
import com.fmi.javaee.vertex.task.Status;
import com.fmi.javaee.vertex.task.TaskBean;
import com.fmi.javaee.vertex.user.UserEntity;

public interface TaskData {

	TaskBean createTask(TaskBean task);
	
	TaskBean updateTask(TaskBean task);
	
	TaskBean getTaskById(String id);
	
	List<TaskBean> getTasksByAssignee(UserEntity asignee);
	
	List<TaskBean> getTasksByPriority(Priority priority);
	
	List<TaskBean> getTasksByStatus(Status status);
}
