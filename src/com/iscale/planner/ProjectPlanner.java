package com.iscale.planner;

import java.util.List;

import com.iscale.bean.Task;

public interface ProjectPlanner {
	/**
	 * Method to add task to a project planner
	 * @param task - task to add
	 * @throws TaskException - if task already exist
	 */
	public void addTask(final Task task) throws TaskException;
	/**
	 * Method to print the project details
	 */
	public void printProjectDetails();
	/**
	 * Method to set the task start/end dates based from the task duration.
	 */
	public void execute();
	/**
	 * Return the configured task for a project planner
	 * @return
	 */
	public List<Task> getTask();
}
