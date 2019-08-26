package com.iscale.test;

import org.junit.Test;

import com.iscale.bean.Task;
import com.iscale.planner.ProjectPlanner;
import com.iscale.planner.ProjectPlannerImpl;
import com.iscale.planner.TaskException;

public class ProjectPlannerTest {
	
	public ProjectPlannerTest() {
		
	}

	@Test
	public void printProjectPlanner() throws TaskException {
		ProjectPlanner projectPlanner = new ProjectPlannerImpl("sample project");
		
		Task task1 = new Task();
		task1.setTaskName("Pre Task 1");
		task1.setDuration(2);
		
		Task task2 = new Task();
		task2.setTaskName("Task 2");
		task2.setDuration(5);
		task2.getDependencies().add(task1);
		
		Task task3 = new Task();
		task3.setTaskName("Task 3");
		task3.setDuration(3);
		task3.getDependencies().add(task2);

		projectPlanner.addTask(task3);
		
		
		Task task11 = new Task();
		task11.setTaskName("Task 11");
		task11.setDuration(3);
		
		Task task12 = new Task();
		task12.setTaskName("Task 12");
		task12.setDuration(4);
		task12.getDependencies().add(task11);
		
		Task task13 = new Task();
		task13.setTaskName("Task 13");
		task13.setDuration(6);

		projectPlanner.addTask(task12);
		projectPlanner.addTask(task13);
		projectPlanner.execute();
		projectPlanner.printProjectDetails();
	}
	
	@Test(expected = TaskException.class)
	public void duplicateProjectPlannerTask() throws TaskException {
		ProjectPlanner projectPlanner = new ProjectPlannerImpl("sample project");
		
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setDuration(2);
		
		Task task2 = new Task();
		task2.setTaskName("Task 1");
		task2.setDuration(5);
		
		projectPlanner.addTask(task1);
		projectPlanner.addTask(task2);
	}


}
