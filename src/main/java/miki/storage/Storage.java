package miki.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import miki.exception.MikiException;

import miki.task.Task;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws MikiException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(Task.parseTaskFromFile(line));
            }
            br.close();
        } catch (IOException e) {
            throw new MikiException("Error loading tasks from file.\n" + e.getMessage());
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws MikiException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toStorageFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new MikiException("Error saving tasks to file.");
        }
    }
}
