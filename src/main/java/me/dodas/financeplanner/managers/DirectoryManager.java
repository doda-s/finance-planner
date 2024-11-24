package me.dodas.financeplanner.managers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryManager {
    File file;
    private String rootPath = System.getProperty("user.dir");
    static private DirectoryManager directoryManager = new DirectoryManager();
    
    private DirectoryManager(){}
    
    public static DirectoryManager getInstance() {
        return directoryManager;
    }
    
    public String getRootPath(){
        return rootPath;
    }

    public void createDirectory(String path, String directoryName){
        try{
            file = new File(path, directoryName);
            if(!file.exists()){
                boolean success = file.mkdir();
                System.out.println("Directory created with success: " + success);
            }else{
                System.out.println("Directory already exists! - " + file.getAbsolutePath());
            }
            
        }
        catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void deleteDirectory(String path, String directoryName){
        try{
            file = new File(path, directoryName);
            if(file.exists()){
                boolean delete = file.delete();
                System.out.println("Directory deleted with success: " + delete);
            }else{
                System.out.println("Directory doesn't exists! - " + file.getAbsolutePath());
            }
        }
        catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public boolean checkDirectory(String path){
        Path directoriesPath = Paths.get(path);
        return Files.exists(Paths.get(path)) && Files.isDirectory(directoriesPath);
    }

    public String[] getDirectories (String path){
        File file = new File(path);
        List<String> directoriesPath = new ArrayList<>();

        if(file.exists()){
            File[] folders = file.listFiles(File::isDirectory);
            for(File folder : folders){
                directoriesPath.add(folder.getName());
            }
            return directoriesPath.toArray(new String[0]);
        }
        System.out.println("Directory '"+path+"' doesn't exist.");
        return null;
        
    }

    public String[] getDirectoriesPath (String path){
        File file = new File(path);
        List<String> directoriesPath = new ArrayList<>();

        if(file.exists()){
            File[] folders = file.listFiles(File::isDirectory);
            for(File folder : folders){
                directoriesPath.add(folder.getAbsolutePath());
            }
            return directoriesPath.toArray(new String[0]);
        }
        System.out.println("Directory '"+path+"' doesn't exist.");
        return null;
        
    }
}
