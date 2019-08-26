package com.iscale.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * This class will hold the Task bean
 *
 */
public class Task {

	/* variable for task name */
	private String taskName;
	/* variable for start date */
	private Date startDate;
	/* variable for end date */
	private Date endDate;
	/* variable for duration */
	private int duration;
	/* variable if current task has corresponding dependency */
	private List<Task> dependencies;
	
	public Task() {
		dependencies = new ArrayList<Task>();
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<Task> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Task> dependencies) {
		this.dependencies = dependencies;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
