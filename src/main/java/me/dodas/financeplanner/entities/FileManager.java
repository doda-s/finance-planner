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

    // Cria um arquivo baseado no path
    public void createFile(String path, String fileContent) {
        Path filePath = Paths.get(path);
        try {

            FileWriter fileWriter = new FileWriter(filePath.toFile());
            fileWriter.write(fileContent);
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    // Escreve o arquivo baseado no path
    public void writeFile(String path, String fileContent) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(fileContent);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error trying to write a file.");
        }
    }

    // Deleta um arquivo baseado no path
    public void deleteFile(String path) {
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

    // Retorna um array de nomes dos arquivos que contém alguma das extensões passadas no param
    public String[] getFilesName(String directory, String[] extensions) {
        File[] files = new File(directory).listFiles(File::isFile);
        List<String> filesName = new ArrayList<>();
        
        if (extensions != null) {

            for(File file : files){
                if (checkFileExtension(file.getName(), extensions)) {
                    filesName.add(file.getName());
                }
            }

            return filesName.toArray(new String[0]);
        }

        for(File file : files) {
            filesName.add(file.getName());
        }

        return filesName.toArray(new String[0]);
    }

    // Retorna um array de absolute paths dos arquivos que contém alguma das extensões passadas no param
    public String[] getFilesPath(String directory, String[] extensions) {
        File[] files = new File(directory).listFiles(File::isFile);
        List<String> filesPath = new ArrayList<>();
        
        if (extensions != null) {

            for(File file : files){
                if (checkFileExtension(file.getName(), extensions)) {
                    filesPath.add(file.getAbsolutePath());
                }
            }

            return filesPath.toArray(new String[0]);
        }

        for(File file : files) {
            filesPath.add(file.getAbsolutePath());
        }

        return filesPath.toArray(new String[0]);
    }

    // Verifica se o nome do arquivo termina com alguma das extensões passadas no param
    private boolean checkFileExtension(String fileName, String[] extensions) {

        for(String ext : extensions) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;

    }
}
