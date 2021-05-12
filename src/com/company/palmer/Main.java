package com.company.palmer;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;

class Task {
    private String title;
    private String description;
    private String priority;

    public Task(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle(String title) {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription(String description) {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority(String priority) {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task: " + title + "\nDescription: " + description + "\nPriority: " + priority +"\n";
    }

    static void menu() {
        System.out.println("Please choose an option:");
        System.out.println("(1) Add a task.");
        System.out.println("(2) Remove a task.");
        System.out.println("(3) Update a task.");
        System.out.println("(4) List all tasks.");
        System.out.println("(5) List tasks with selected priority.");
        System.out.println("(0) Exit.");
    }

    static List<Task> add(List<Task> tasks, int index) {
        System.out.println("Enter the name of the task you would like to add or 'cancel' to exit: ");
        String taskTitle = user();
        String taskDescription = "";
        String taskPriority = "";
        int valid = 0;
        while (valid == 0) {
            if (taskTitle.trim().equalsIgnoreCase("cancel")) {
                break;
            } else {
                try {
                    while (taskTitle.trim().equals("") || Integer.parseInt(taskTitle) < 2147483647) {
                        System.out.println("Please enter a real task or 'cancel' to exit. Press 'enter' to continue.");
                        user();
                        System.out.println("Enter the task would you like to add or 'cancel' to exit: ");
                        taskTitle = user();
                        if (taskTitle.trim().equalsIgnoreCase("cancel")) {
                            break;
                        }
                    }
                } catch (NumberFormatException e) {

                }

                System.out.println("Please enter a description of the task: ");
                taskDescription = user();

                try {
                    while (taskDescription.trim().equals("") || Integer.parseInt(taskDescription) < 2147483647) {
                        System.out.println("Please enter a real description: ");
                        taskDescription = user();
                    }
                } catch (NumberFormatException e) {

                }
                System.out.println("Now enter the priority of the task from 0 to 5 (5 being high priority and 0 being low priority): ");
                taskPriority = user();
                valid = 2;
                while (valid == 2) {
                    try {
                        if (valid == 2) {
                            while (taskPriority.trim().equals("") || Integer.parseInt(taskPriority) > 5) {
                                System.out.println("Please enter a real priority from 0 to 5. ");
                                taskPriority = user();
                            }
                        }
                        valid = 0;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a number between 0 and 5. Remember, 5 means high priority while 0 means low priority: ");
                        taskPriority = user();

                    }
                }

            }
            valid = 1;
        }

        System.out.println("Press 'enter' to continue.");
        user();

        if(!taskTitle.trim().equals("cancel")) {
            Task newTask = new Task(taskTitle, taskDescription, taskPriority);

            tasks.add(index, newTask);

            index++;
        }
        return tasks;
    }

    static List<Task> remove(List<Task> tasks, int index) {
        if (tasks.size() < 1) {
            System.out.println("You have no tasks listed. Press 'enter' to continue.");
            user();
        } else {
            int valid = 0;
            while (valid == 0) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("(" + i + ") " + tasks.get(i));
                }
                System.out.println("Enter the index of the task you'd like to remove or 'cancel' to exit: ");
                String user = user();
                if (user.trim().equalsIgnoreCase("cancel")) {
                    break;
                }
                int i = 0;
                while (i < tasks.size()) {
                    try {
                        if (Integer.parseInt(user) == i) {
                            tasks.remove(i);
                            valid += 1;
                        }
                        i += 1;
                    } catch (NumberFormatException e) {
                        i += 1;
                    }
                }
                if (valid == 0) {
                    System.out.println("Please enter a valid index or 'cancel' to exit. Press 'enter' to try again.");
                    user();
                }
            }
            System.out.println("Press 'enter' to continue.");
            user();
        }

        return tasks;
    }

    static Task update(List<Task> tasks, int index) {
        String cancel = "";
        String taskTitle = "";
        String taskDescription = "";
        String taskPriority = "";

        if (tasks.size() < 1) {
            System.out.println("You have no tasks listed. Press 'enter' to continue.");
            user();
        } else {
            int valid = 0;
            while (valid == 0) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("(" + i + ") " + tasks.get(i));
                }
                System.out.println("Enter the index of the task you'd like to update or 'cancel' to exit: ");
                taskTitle = user();
                if (taskTitle.trim().equalsIgnoreCase("cancel")) {
                    cancel = "cancel";
                    break;
                } else {
                    int i = 0;
                    while (i < tasks.size()) {
                        try {
                            if (Integer.parseInt(taskTitle) == i) {
                                tasks.remove(i);
                                valid += 1;
                            }
                            i += 1;
                        } catch (NumberFormatException e) {
                            i += 1;
                        }
                    }
                    if (valid == 0) {
                        System.out.println("Please enter a valid index or 'cancel' to exit. Press 'enter' to try again.");
                        user();
                    }
                }
            }
            if (cancel.equals("cancel")) {
                cancel = "";
                //Do nothing and default back to main menu.
            } else {
                System.out.println("What would you like to update this task to?: ");
                valid = 0;
                while (valid == 0) {
                    if (taskTitle.trim().equalsIgnoreCase("cancel")) {
                        cancel = "cancel";
                        break;
                    } else {
                        try {
                            while (taskTitle.trim().equals("") || Integer.parseInt(taskTitle) < 2147483647) {
                                System.out.println("Please enter a real task or 'cancel' to exit. Press 'enter' to continue.");
                                user();
                                System.out.println("What would you like to update this task to?: ");
                                taskTitle = user();
                            }
                        } catch (NumberFormatException e) {
                            valid += 1;
                        }
                    }
                    System.out.println("Please enter a description of the task: ");
                    taskDescription = user();

                    try {
                        while (taskDescription.trim().equals("") || Integer.parseInt(taskDescription) < 2147483647) {
                            System.out.println("Please enter a real description: ");
                            taskDescription = user();
                        }
                    } catch (NumberFormatException e) {

                    }
                    System.out.println("Now enter the priority of the task from 0 to 5 (5 being high priority and 0 being low priority): ");
                    taskPriority = user();
                    valid = 2;
                    while (valid == 2) {
                        try {
                            if (valid == 2) {
                                while (taskPriority.trim().equals("") || Integer.parseInt(taskPriority) > 5) {
                                    System.out.println("Please enter a real priority from 0 to 5. ");
                                    taskPriority = user();
                                }
                            }
                            valid = 0;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a number between 0 and 5. Remember, 5 means high priority while 0 means low priority: ");
                            taskPriority = user();

                        }
                    }
                    valid = 1;
                }
            }
        }
        System.out.println("Press 'enter' to continue.");
        user();

        Task newTask = new Task(taskTitle, taskDescription, taskPriority);

        tasks.add(index, newTask);

        index++;

        return newTask;
    }


    static void list(List<Task> tasks, int index) {
        if (tasks.size() == 0) {
            System.out.println("\nYou have not listed any tasks this session. Press 'enter' to continue.");
            user();
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("(" + i + ") " + tasks.get(i));
            }
            System.out.println("Press 'enter' to continue.");
            user();
        }
    }

    static void listByPriority(List<Task> tasks, int index) {

        if(tasks.size() == 0){
            System.out.println("You have no tasks listed. Press 'enter' to continue.");
            user();
        }else {
            System.out.println("Please enter the priority tasks you would like to list: ");
            String taskPriority = user();
            int valid = 1;
            while (valid == 1) {
                try {
                    if (valid == 1) {
                        while (taskPriority.trim().equals("") || Integer.parseInt(taskPriority) > 5) {
                            System.out.println("Please enter a real priority from 0 to 5. ");
                            taskPriority = user();
                        }
                    }
                    valid = 0;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number between 0 and 5. Remember, 5 means high priority while 0 means low priority: ");
                    taskPriority = user();
                }
            }
            int i = 0;
            for(Task task : tasks) {
                if (Integer.parseInt(taskPriority) == i)
                    System.out.println("(" + i + ") " + tasks.get(i));
                i++;
            }
            System.out.println("Press 'enter' to continue. ");
            user();
        }
    }

    static void exit(List<Task> tasks) {
        Gson gson = new Gson();
        String json = gson.toJson(tasks);
        try{
            FileWriter writer = new FileWriter("data.json");
            gson.toJson(tasks,writer);
            writer.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    static String user() {
        Scanner input = new Scanner(System.in);
        String user = input.nextLine();
        return user;
    }

}

public class Main {

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        int index = 0;
        String user = "1";

        while (user == "1" || user == "2" || user == "3" || user == "4") {

            Task.menu();
            user = Task.user();

            switch (user) {
                case "1":
                    Task.add(tasks, index);
                    user = "1";
                    break;
                case "2":
                    Task.remove(tasks, index);
                    user = "1";
                    break;
                case "3":
                    Task.update(tasks, index);
                    user = "1";
                    break;
                case "4":
                        Gson gson = new Gson();
                        BufferedReader br = null;
                        try {
                            br = new BufferedReader(new FileReader("data.json"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        List<Task> tasks1 = gson.fromJson(br, List.class);
                        System.out.println(tasks1);
                    Task.list(tasks, index);
                    user = "1";
                    break;
                case "5":
                    Task.listByPriority(tasks, index);
                    user = "1";
                    break;
                case "0":
                    Task.exit(tasks);
                    break;
                default:
                    System.out.println("That is in invalid option. Please use the options provided. Press 'enter' to try again.");
                    Task.user();
                    user = "1";

            }
        }
    }
}

