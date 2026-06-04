package Services;

import Entities.User;
//
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    static Scanner sc = new Scanner(System.in);
    //-------------------------------------------------------------
    static ArrayList<User> usuariosList = new ArrayList<>();
    //----------------------METHODS---------------------------------------
    public static void visualizar() {
        System.out.println("\u001B[34m--------------------\u001B[0m Usuários \u001B[34m--------------------\u001B[0m");
        System.out.printf("%-10s %-20s %-10s %-12s%n","ID", "NOME", "IDADE", "SENHA");
        System.out.println("--------------------------------------------------");

        for (User user : usuariosList) {
            System.out.printf("%-10s %-20s %-10s %-12s%n",
                    user.getId(),
                    user.getUsuario(),
                    user.getIdade(),
                    user.getSenha()
            );
        }
        System.out.println("\u001B[34m--------------------------------------------------\u001B[0m");
    }

    //-------------------------------------------------------------
    public static void cadastro(){

        boolean terminar = false;
        while(!terminar){
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
                    letraPorLetra("\u001B[31m[ERRO]Sua senha deve conter ao menos 6 digitos, tente novamente.\u001B[0m \n");
                }
            }while(senha.length() < 6);
            usuario.setSenha(senha);

            System.out.println("---------------------------");
            cronometro(100);
            //Adiciona o usuario à lista
            try{
                usuariosList.add(usuario);
            } catch (RuntimeException e) {
                System.out.println("\u001B[31mNão foi possível cadastrar o usuário. Tente novamente mais tarde!\u001B[0m");
            }

            System.out.print("Deseja continuar? [S/N] ");
            String escolhaCadastro = String.valueOf(sc.nextLine().charAt(0)).trim().toUpperCase();


            while(!escolhaCadastro.equals("S") && !escolhaCadastro.equals("N")){
                System.out.print("\u001B[31mDigite uma opção válida!\u001B[0m ");
                escolhaCadastro = String.valueOf(sc.nextLine().charAt(0)).trim().toUpperCase();
            }

            if (escolhaCadastro.equals("N")) {
                terminar = true;
            }
        }

    }
    //-------------------------------------------------------------
    public static void atualizar(){
        System.out.println("-----------Atualizar usuários-----------");
        visualizar();
        System.out.print("\u001B[36mDigite o ID do usuário que deseja atualizar:\u001B[0m ");
        boolean sair = false;
        while(!sair){
            String entrada = sc.nextLine();
            int id = Integer.parseInt(entrada);
            for(User i : usuariosList){
                if(i.getId() != id){
                    System.out.println("\u001B[31mID não encontrado. Tente novamente!\u001b[0m");
                }else {
                    System.out.println("O que deseja atualizar?");
                    System.out.println("[1] Nome");
                    System.out.println("[2] Idade");
                    System.out.println("[3] Senha");
                    String escolha = sc.nextLine();
                    int opcao = 0;
                    while(true){
                        try{
                            opcao = Integer.parseInt(escolha);

                            if(opcao == 1 || opcao == 2 || opcao == 3){
                                break;
                            } else{
                                letraPorLetra("\u001B[31m[ERRO] Você digitou uma opção inválida!\u001B[0m \n");

                                letraPorLetra("[VOLTANDO PARA O MENU] \n");
                                break;
                            }
                        } catch ( NumberFormatException e){
                            letraPorLetra("\u001B[31m[ERRO] Isso não é um número! tente novamente\u001B[0m \n");

                            letraPorLetra("\u001B[33m[VOLTANDO PARA O MENU]\u001B[0m \n");
                            cronometro(500);
                            break;
                        }
                    }
                    switch (opcao){
                        case 1:
                            letraPorLetra("Digite o novo nome do usuário: ");
                            if(i.getId() == id ){
                                String novoNome = sc.nextLine();
                                i.setUsuario(novoNome);
                                sair = true;
                            }
                            break;
                        case 2:
                            if(i.getId() == id){
                                boolean idadeValida = false;
                                int novaIdade = 0;
                                do{
                                    letraPorLetra("Digite a nova idade do usuário: ");
                                    String input = sc.nextLine();
                                    try{
                                        novaIdade = Integer.parseInt(input);
                                        if(novaIdade < 0){
                                            letraPorLetra("\u001B[31m[ERRO] Digite a idade corretamente\u001B[0m \n");
                                        } else{
                                            idadeValida = true;
                                        }
                                    } catch (NumberFormatException e) {
                                        letraPorLetra("\u001B[31m[ERRO] Erro ao definir idade!\u001B[0m \n");

                                    }
                                }while(!idadeValida);
                                i.setIdade(novaIdade);
                                sair = true;
                            }
                            break;
                        case 3:
                            String novaSenha;
                            do{
                                letraPorLetra("Digite a nova senha [6 digitos]: ");
                                novaSenha = sc.nextLine();
                                if(novaSenha.length() < 6){
                                    letraPorLetra("\u001B[31m[ERRO]Sua senha deve conter ao menos 6 digitos, tente novamente.\u001B[0m \n");
                                }
                            }while(novaSenha.length() < 6);
                            i.setSenha(novaSenha);
                            sair = true;
                            break;
                    }
                }
            }
        }

    }
    //-------------------------------------------------------------
    public static void menu() throws InterruptedException {
        boolean ficar = true;
        while(ficar){

            System.out.println("-----------Menu-----------");
            System.out.println("[1] Cadastro");
            System.out.println("[2] Visualizar usuários");
            System.out.println("[3] Atualizar usuários");
            System.out.println("[4] Finalizar programa");
            System.out.println("--------------------------");
            System.out.print("DIGITE A OPÇÃO DESEJADA: ");
            int opcao = 0;
            String entrada  = sc.nextLine();

            while(true){
                try{
                    opcao = Integer.parseInt(entrada);

                    if(opcao == 1 || opcao == 2 || opcao == 3 || opcao == 4){
                        break;
                    } else{
                        letraPorLetra("\u001B[31m[ERRO] Você digitou uma opção inválida!\u001B[0m \n");

                        letraPorLetra("[VOLTANDO PARA O MENU] \n");
                        break;
                    }
                } catch ( NumberFormatException e){
                    letraPorLetra("\u001B[31m[ERRO] Isso não é um número! tente novamente\u001B[0m \n");

                    letraPorLetra("\u001B[33m[VOLTANDO PARA O MENU]\u001B[0m \n");
                    cronometro(500);
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
                    atualizar();
                    break;
                case 4:
                    ficar = false;
                    System.out.println("\u001B[36mFinalizando programa...\u001B[0m");
                    break;
            }

        }
    }
    //-------------------------------------------------------------------
    public static void letraPorLetra(String mensagem){
        for (char caractere : mensagem.toCharArray()) {
            System.out.print(caractere);
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    public static void cronometro(long milis){
        try{
            Thread.sleep(milis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
