package Application;

import Entities.User;

import java.util.Scanner;
import Services.UserService;
public class Program {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            UserService.menu();
//
        } catch (InterruptedException e) {
            System.out.println("\u001B[31m[ERRO] Ocorreu um erro ao abrir o menu\u001B[0m");
        }
    }
}
