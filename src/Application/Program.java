package Application;

import Entities.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Services.UserService;
public class Program {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            UserService.menu();
            File file = new File("F:\\coisas Ravi\\projetos\\CRUD-Simples\\src\\Repository\\USUARIOS.txt");
            if(!file.exists()){

                FileWriter fw = new FileWriter(file,true);
                fw.write("");

                System.out.println("Arquivo criado com sucesso");
            }


        } catch (InterruptedException | IOException e) {
            System.out.println("\u001B[31m[ERRO] Ocorreu um erro ao abrir o menu\u001B[0m");
        }
    }
}
