package com.iscale.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 *  Class that will hold the ProjectPlan bean 
 *
 */
public class ProjectPlan {

	/* variable for project name */
	private String projectName;
	/* variable for list of task */
	private List<Task> taskList;
	
	public ProjectPlan(String name) {
		projectName = name;
		taskList = new ArrayList<Task>();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
}
