package com.iscale.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.iscale.bean.Task;

/**
 * Utility class for Project Planner
 * 
 *
 */
public class PlannerUtil {
	private static Calendar datePicker = Calendar.getInstance();
	
	/**
	 * Populate the start and end dates of each task, including its dependent task
	 * @param task - Task object
	 */
	public static void populateDates(Task task) {
		datePicker.add(Calendar.DAY_OF_MONTH, 1);
		if (task.getDependencies().isEmpty()) {
			setTaskDate(task);
		} 
		for(final Task dependentTask : task.getDependencies()) {
			if (dependentTask.getDependencies().isEmpty()) {
				setTaskDate(dependentTask);
				datePicker.add(Calendar.DAY_OF_MONTH, 1);
			} else {
				populateDates(dependentTask);
			}
		}
		setTaskDate(task);
	}

	/**
	 * Set the start and end dates of a task
	 * @param task - Task object
	 */
	private static void setTaskDate(final Task task) {
		Date startDate = getWeekDay(datePicker.getTime());
		task.setStartDate(startDate);
		datePicker.setTime(startDate);
    	for(int i = 1; i < task.getDuration(); i++) {
			datePicker.add(Calendar.DAY_OF_MONTH, 1);
			Date endDate = getWeekDay(datePicker.getTime());
			datePicker.setTime(endDate);
		}
		task.setEndDate(datePicker.getTime());
	}
	
	/**
	 * Return the only the week day of the month, 
	 * @param date - Date object
	 * @return Date - week day
	 */
    private static Date getWeekDay(final Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	if (Calendar.SATURDAY == dayOfWeek) {
    		cal.add(Calendar.DAY_OF_MONTH, 2);
    	}
    	if (Calendar.SUNDAY == dayOfWeek)
		{
    		cal.add(Calendar.DAY_OF_MONTH, 1);
		}
    	Date returnDate = cal.getTime();
    	return returnDate;
    }

    /**
     * Print the task details of a given task list
     * @param tasks - list of task
     */
    public static void printTask(final List<Task> tasks) {
		for(final Task task : tasks) {
	    	printTaskDetails(task);
		}
	}
    
    /**
     * Print the task details
     * @param task - Task object
     */
    public static void printTaskDetails(final Task task) {
    	System.out.println("Task name : " + task.getTaskName());
    	System.out.println("Task start date " +task.getStartDate());
    	System.out.println("Task end date " +task.getEndDate());
    	System.out.println("Task has dependencies  " + (task.getDependencies().size() > 0));
    	if ((task.getDependencies().size() > 0)) {
	    	System.out.println(task.getTaskName() +" is dependent to the following task : ");
			for(final Task dependentTask : task.getDependencies()) {
		    	System.out.println("	- Task name : " + dependentTask.getTaskName());
			}
    	}
    	System.out.println("");
	}
    
}
