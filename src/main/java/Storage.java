import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private static String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public void saveList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(Storage.filePath);
            StringBuilder tasks = new StringBuilder();
            ArrayList<Task> taskArrayList = taskList.getArrayList();

            for (int i = 0;i < taskArrayList.size();i++) {
                tasks.append(taskArrayList.get(i).getTaskString() + "\n");
            }
            fw.write(tasks.toString());
            fw.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public ArrayList<Task> loadList() throws FileNotFoundException {
        
        ArrayList<Task> taskList = new ArrayList<>();

        File taskListFile = new File(Storage.filePath); 
        Scanner taskListScanner = new Scanner(taskListFile);
        while (taskListScanner.hasNext()) {
            taskList.add(processStorageInput(taskListScanner.nextLine()));
        }
        taskListScanner.close();
        return taskList;

    }

    public static Task processStorageInput(String input) {
        String[] splitInput = input.split(" \\| ");
        String typeOfTask = splitInput[0];
        boolean isDone = splitInput[1].equals("X") ? true : false;
        Task task;

        if (typeOfTask.equals("T")) {
            task = new ToDo(splitInput[2]);
        } else if (typeOfTask.equals("D")) {
            task = new Deadline(splitInput[2], LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER));
        } else {
            task = new Event(splitInput[2], LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER), LocalDateTime.parse(splitInput[4], DATE_TIME_FORMATTER));
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
