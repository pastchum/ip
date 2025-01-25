package miki.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import miki.exception.MikiException;
import miki.parse.Parser;
import miki.task.*;

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
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(Parser.parseTaskFromFile(line));
            }
            br.close();
        } catch (IOException e) {
            throw new MikiException("Error loading tasks from file.");
        }
        return tasks;
    }

    public void save(List<String> tasks) throws MikiException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new MikiException("Error saving tasks to file.");
        }
    }
}
