import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public boolean updateData(String key, String name, int value) {
        List<String> lines = getData();

        boolean updated = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.split(":")[0].equals(key)) {
                lines.set(i, name + ": " + value);
                updated = true;
            }
        }

        try {
            Files.write(Path.of(directory), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public boolean deleteData(String key) {
        List<String> lines = getData();

        boolean deleted = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.split(":")[0].equals(key)) {
                lines.remove(i);
                deleted = true;
            }
        }

        try {
            Files.write(Path.of(directory), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deleted;
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
