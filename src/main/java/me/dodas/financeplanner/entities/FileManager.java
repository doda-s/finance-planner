package me.dodas.financeplanner.entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    static private FileManager fileManagerInstance = new FileManager();
    
    private FileManager(){}

    static public FileManager getInstance() {
        return fileManagerInstance;
    }

    public void createJsonFile(String path, String fileContent) {
        Path filePath = Paths.get(path);
        try {

            FileWriter fileWriter = new FileWriter(filePath.toFile());
            fileWriter.write(fileContent);
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    public void updateJsonFile(String path) {

    }

    public void deleteJsonFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            System.out.println("An error occurred while deleting a file.");
        }
    }

    public boolean checkFile(String path) {
        Path file = Paths.get(path);
        return Files.exists(file) && Files.isRegularFile(file);
    }

    public String[] getFilesName(String directory, String... extensions) {
        File[] files = new File(directory).listFiles(File::isFile);
        List<String> filesName = new ArrayList<>();
        
        if (extensions != null) {
            
        }

        for(File file : files) {
            filesName.add(file.getName());
        }

        return filesName.toArray(new String[0]);
    }

    public String[] getFilesPath(String directory, String... extensions) {
        File[] files = new File(directory).listFiles(File::isFile);
        List<String> filesPath = new ArrayList<>();
        
        if (extensions != null) {
            
        }

        for(File file : files) {
            filesPath.add(file.getAbsolutePath());
        }

        return filesPath.toArray(new String[0]);
    }
}
