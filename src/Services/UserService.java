package Services;

import Entities.User;
//
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    static Scanner sc = new Scanner(System.in);
    //-------------------------------------------------------------
    static ArrayList<User> usuariosList = new ArrayList<User>();
    //----------------------METHODS---------------------------------------
    public static void visualizar(){

    }
    //-------------------------------------------------------------
    public static void cadastro(){
        System.out.println("---------Cadastro---------");
        User usuario = new User();


        letraPorLetra("Digite seu nome de usuário: ");
        usuario.setUsuario(sc.nextLine().trim());

        int idade = 0;
        boolean idadeValida = false;

        //LOOP QUE VERIFICA IDADE
        do{
            letraPorLetra("Digite sua idade: ");
            String entrada = sc.nextLine();
            try{
                idade = Integer.parseInt(entrada);
                if(idade < 0){
                    letraPorLetra("\u001B[31m[ERRO] Digite a idade corretamente\u001B[0m \n");
                } else{
                    idadeValida = true;

                }

            } catch (NumberFormatException e) {
                letraPorLetra("\u001B[31m[ERRO] Erro ao definir idade!\u001B[0m \n");

            }

        }while(!idadeValida);
        usuario.setIdade(idade);

        String senha;

        //LOOP QUE VERIFICA A SENHA

        do{
            letraPorLetra("Digite sua senha [6 digitos]: ");
            senha = sc.nextLine();
            if(senha.length() < 6){
                letraPorLetra("\u001B[31m[ERRO]Senha inválida, tente novamente.\u001B[0m");
            } else{
                letraPorLetra("[VOLTANDO AO MENU]");
            }
        }while(senha.length() < 6);
        usuario.setSenha(senha);

        System.out.println("---------------------------");
        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //Adiciona o usuario à lista
        try{
            usuariosList.add(usuario);
        } catch (RuntimeException e) {
            System.out.println("\u001B[31mNão foi possível cadastrar o usuário. Tente novamente mais tarde!\u001B[0m");
        }

    }
    //-------------------------------------------------------------
    public static void menu() throws InterruptedException {
        boolean ficar = true;
        while(ficar){

            System.out.println("-----------Menu-----------");
            System.out.println("[1] Cadastro");
            System.out.println("[2] Finalizar programa");
            System.out.println("--------------------------");
            System.out.print("DIGITE A OPÇÃO DESEJADA: ");
            int opcao = 0;
            String entrada  = sc.nextLine();
            while(true){
                try{

                    opcao = Integer.parseInt(entrada);

                    if(opcao == 1 || opcao == 2 || opcao == 3){
                        break;
                    } else{
                        letraPorLetra("\u001B[31m[ERRO] Você digitou uma opção inválida!\u001B[0m \n");

                        letraPorLetra("[VOLTANDO PARA O MENU] \n");
                        break;
                    }
                } catch ( NumberFormatException e){
                    letraPorLetra("\u001B[31m[ERRO] Isso não é um número! tente novamente\u001B[0m \n");

                    letraPorLetra("[VOLTANDO PARA O MENU] \n");
                    Thread.sleep(1500);
                    break;
                }
            }

            switch (opcao){
                case 1: cadastro();
                    break;
                case 2:
                    visualizar();
                    break;
                case 3:
                    ficar = false;
                    System.out.println("Finalizando programa...");
                    break;
            }

        }
    }
    //-------------------------------------------------------------------
    public static void letraPorLetra(String mensagem){
        for (char caractere : mensagem.toCharArray()) {
            System.out.print(caractere);
            try{
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
