package com.iscale.planner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.iscale.bean.ProjectPlan;
import com.iscale.bean.Task;
import com.iscale.util.PlannerUtil;

public class ProjectPlannerImpl implements ProjectPlanner {
	
	private ProjectPlan projectPlanner;
	
	public ProjectPlannerImpl(final String name) {
		projectPlanner = new ProjectPlan(name);
	}
	

	private void validateTask(List<Task> taskList, Task task) throws TaskException{
		for(final Task savedTask : taskList) {
			if (savedTask.getTaskName().equals(task.getTaskName())) {
				throw new TaskException(task.getTaskName() + "already exist");
			}
		}
	}

	@Override
	public void addTask(Task task) throws TaskException{
		final List<Task> taskList = projectPlanner.getTaskList();
		validateTask(taskList, task);
		taskList.add(task);
	}

	@Override
	public void printProjectDetails() {
    	System.out.println("Project name : " + projectPlanner.getProjectName());
		final List<Task> taskList = projectPlanner.getTaskList();
		final List<Task> sortTaskDate = sortDate(taskList);
		PlannerUtil.printTask(sortTaskDate);
	}
	
	private List<Task> sortDate(final List<Task> taskList) {
		
		final List<Task> allTask = new ArrayList<Task>();
		getAllTask(taskList, allTask);
        Collections.sort(allTask, new Comparator<Task>(){
        	 
            @Override
            public int compare(Task task1, Task task2) {
            	final Date startDate1 = task1.getStartDate();
            	final Date startDate2 = task2.getStartDate();
                return startDate1.compareTo(startDate2);
            }
        });
        return allTask;
	}
	
	
    private void getAllTask(final List<Task> tasks, final List<Task> allTask){
		for(final Task dependentTask : tasks) {
			if (dependentTask.getDependencies().isEmpty()) {
				allTask.add(dependentTask);
			} else {
				allTask.add(dependentTask);
				getAllTask(dependentTask.getDependencies(),allTask);
			}
		}

    }



	@Override
	public void execute() {
		final List<Task> taskList = projectPlanner.getTaskList();
		for(final Task task : taskList) {
			PlannerUtil.populateDates(task);
		}		
	}


	@Override
	public List<Task> getTask() {
		return projectPlanner.getTaskList();
	}
}
