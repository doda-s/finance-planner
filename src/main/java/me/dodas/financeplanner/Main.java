package me.dodas.financeplanner;

import me.dodas.financeplanner.entities.FileManager;

public class Main {
    public static void main(String[] args) {
        String[] names = FileManager.getInstance().getFilesName("C:/Users/pedro.schuch/Desktop/Teste");
        String[] paths = FileManager.getInstance().getFilesPath("C:/Users/pedro.schuch/Desktop/Teste");
        for(String name : names) {
            System.out.println(name);
        }

        System.out.println("\n-----------------------------------\n");
        FileManager.getInstance().createJsonFile("C:/Users/pedro.schuch/Desktop/Teste/fui_criado_pela_classe_do_pedro.txt", "Fui criado pela classe do Pedro");
        names = FileManager.getInstance().getFilesName("C:/Users/pedro.schuch/Desktop/Teste");

        
        for(String name : names) {
            System.out.println(name);
        }
        
        System.out.println("\n-----------------------------------\n");
        FileManager.getInstance().deleteJsonFile("C:/Users/pedro.schuch/Desktop/Teste/fui_criado_pela_classe_do_pedro.txt");
        names = FileManager.getInstance().getFilesName("C:/Users/pedro.schuch/Desktop/Teste");
        
        for(String name : names) {
            System.out.println(name);
        }

        System.out.println("\n-----------------------------------\n");
        
        for(String path : paths) {
            System.out.println(path);
        }

        System.out.println("\n-----------------------------------\n");
        FileManager.getInstance().createJsonFile("C:/Users/pedro.schuch/Desktop/Teste/fui_criado_pela_classe_do_pedro.txt", "Fui criado pela classe do Pedro");
        System.out.printf("fui_criado_pela_classe_do_pedro.txt: %s\n", FileManager.getInstance().checkFile("C:/Users/pedro.schuch/Desktop/Teste/fui_criado_pela_classe_do_pedro.txt"));
        System.out.printf("Arquivo que eu sei que não existe: %s\n", FileManager.getInstance().checkFile("C:/Users/pedro.schuch/Desktop/Teste/aa_follow.txt"));
        System.out.printf("Arquivo que eu sei que não existe: %s\n", FileManager.getInstance().checkFile("C:/Users/pedro.schuch/Desktop/Teste/Pasta_de.Teste"));
    }  
}