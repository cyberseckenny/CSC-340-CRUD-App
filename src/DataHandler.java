import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public
class DataHandler {
    private String directory;

    public DataHandler(String directory) {
        this.directory = directory;
    }

    public List<String> getData() {
        try {
            return Files.readAllLines(Path.of(directory), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateData(String key, int value) {
        List<String> lines = getData();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.split(":")[0].equals(key)) {
                lines.set(i, key + ": " + value);
            }
        }

        try {
            Files.write(Path.of(directory), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(String key) {
        List<String> lines = getData();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.split(":")[0].equals(key)) {
                lines.remove(i);
            }
        }

        try {
            Files.write(Path.of(directory), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newData(String key, int i) {
        try {
            FileWriter writer = new FileWriter(directory, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(key + ": " + i);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
