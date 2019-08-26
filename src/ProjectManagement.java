import java.util.Scanner;

import com.iscale.bean.Task;
import com.iscale.planner.ProjectPlanner;
import com.iscale.planner.ProjectPlannerImpl;
import com.iscale.planner.TaskException;

public class ProjectManagement {

	public static void main(String[] args) {
		ProjectPlanner projectPlanner = null;
		menu();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter Project Name ");
				String name = getStringUserInput(scanner);
				projectPlanner = new ProjectPlannerImpl(name);
				break;
			case 2:
				if (null == projectPlanner) {
			        System.out.println("Create Project First");
				} else {
					task(projectPlanner, scanner);
				}
				break;
			case 3:
				if (validate(projectPlanner)) {
					printProject(projectPlanner);
				}
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Read the options carefully...");
			}
			menu();
		}

	}
	
	private static void menu() {
        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Create Project");
        System.out.println("2 - Add Task");
        System.out.println("3 - Print Project Details");
        System.out.println("4 - Quit");
		
	}
	
	private static void printProject(ProjectPlanner projectPlanner) {
		projectPlanner.execute();
		projectPlanner.printProjectDetails();
	}
	
	private static void task(ProjectPlanner projectPlanner, Scanner scanner)  {
        System.out.println("Enter Task Name ");
        String name = getStringUserInput(scanner);
        Task task = new Task();
        task.setTaskName(name);
        System.out.println("Enter Task Duration ");
        int duration = getIntUserInput(scanner);
        task.setDuration(duration);
        System.out.println("Task has dependency [true/false]? ");
        boolean b = getBooleanUserInput(scanner);
        if (b) {
        	Task dependencyTask = addTaskDependency(scanner);
        	task.getDependencies().add(dependencyTask);
        }
        try {
        	projectPlanner.addTask(task);
        } catch (TaskException e) {
        	System.out.println("exception caught " + e.getMessage());
        	menu();
        }
        
	}
	
	private static Task addTaskDependency(Scanner scanner) {
        System.out.println("Enter Task Name ");
        String name = getStringUserInput(scanner);
        Task task = new Task();
        task.setTaskName(name);
        System.out.println("Enter Task Duration ");
        int duration = getIntUserInput(scanner);
        task.setDuration(duration);
        return task;
	}
	
	private static boolean validate(ProjectPlanner projectPlanner) {
		if (null == projectPlanner) {
	        System.out.println("Create Project First");
	        return false;
		} else if (projectPlanner.getTask().isEmpty()) {
	        System.out.println("No assigned Task to Project yet");
	        return false;
	    }
		return true;
	}

	private static String getStringUserInput(Scanner scanner) {
		boolean condition = true;
		String input = null;
		while(condition) {
			String name = scanner.nextLine();
			if (name.length() > 0) {
				input = name;
				condition = false;
			}
		}
		return input;
	}

	private static int getIntUserInput(Scanner scanner) {
		boolean condition = true;
		int userInput = -1;
		while(condition) {
			userInput = scanner.nextInt();
			condition = false;
		}
		return userInput;
	}
	private static boolean getBooleanUserInput(Scanner scanner) {
		boolean condition = true;
		boolean flag = false;
		while(condition) {
			flag = scanner.nextBoolean();
			condition = false;
		}
		return flag;
	}
}
