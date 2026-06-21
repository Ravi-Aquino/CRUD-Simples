package Services;

import Entities.User;
//
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserService {
    static Scanner sc = new Scanner(System.in);
    //-----------------------Armazenamento-------------------------
    private static ArrayList<User> usuariosList = new ArrayList<>();
    static File file = new File("F:\\coisas Ravi\\projetos\\CRUD-Simples\\src\\Repository\\USUARIOS.txt");

    //--------------------------METHODS-----------------------------------

    public static String definirNome(User usuario){
        letraPorLetra("Digite seu nome de usuário [Min 4 dígitos]: ");
        String nome = sc.nextLine().trim();

        while(nome.isEmpty() || nome.length() < 4){
            System.out.println("\u001B[31m[ERRO] Nome vazio ou menos de 4 letras. Digite novamente\u001B[0m");
            nome = sc.nextLine().trim();
        }

        usuario.setUsuario(nome);
        return usuario.getUsuario();
    }
    //-------------------------------------------------------------

    public static int definirIdade(User usuario){
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
        return usuario.getIdade();

    }
    //-------------------------------------------------------------

    public static String definirSenha(User usuario){
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
        return usuario.getSenha();

    }
    //----------------------CRUD METHODS---------------------------------------

    public static void visualizar() {
        System.out.println("\u001B[34m--------------------\u001B[0m Usuários \u001B[34m--------------------\u001B[0m");
        System.out.printf("%-10s %-20s %-10s %-12s%n","ID", "NOME", "IDADE", "SENHA");
        System.out.println("--------------------------------------------------");

        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String line;
            //printa cada parte do nome
            while((line = br.readLine()) != null){
                String[] parts = line.split(";");
                System.out.printf("%-10s %-20s %-10s %-12s%n",
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3]
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\u001B[34m--------------------------------------------------\u001B[0m");
    }
    //-------------------------------------------------------------

    public static void cadastro(){
        boolean terminar = false;
        while(!terminar){
            System.out.println("---------Cadastro---------");
            User usuario = new User();

            String[] userDataArray = {
                    String.valueOf(usuario.getId()),
                    definirNome(usuario),
                    String.valueOf(definirIdade(usuario)),
                    definirSenha(usuario)
            };
            //Adiciona o usuario à lista

            try{//cria filewrite e buffered writer
                try(FileWriter fw = new FileWriter(file,true); BufferedWriter bw = new BufferedWriter(fw)) {
                    String linha = String.join(";", userDataArray); //transforma o array em String
                    bw.write(linha);
                    bw.newLine();
                    bw.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

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
        boolean sair = false;
        while(!sair){
            System.out.print("\u001B[36mDigite o ID do usuário que deseja atualizar:\u001B[0m ");

            String entrada = sc.nextLine();
            try{
                int id = Integer.parseInt(entrada);


                boolean encontrou = false;

                for(User i : usuariosList){
                    if(i.getId() == id){
                        encontrou = true;
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
                                definirNome(i);
                                sair = true;
                                break;
                            case 2:
                                definirIdade(i);

                                sair = true;

                                break;
                            case 3:
                                definirSenha(i);
                                sair = true;
                                break;
                        }
                        break;
                    }
                }

                if(!encontrou){
                    System.out.print("\u001B[31mID não encontrado. Deseja sair? [S/N]\u001B[0m ");
                    String escolha = String.valueOf(sc.nextLine().trim().toUpperCase().charAt(0));
                    if(escolha.equals("S")){
                        sair = true;
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("Digite um ID válido!");
                }
        }

    }
    //-------------------------------------------------------------
    public static void deletar(){
        System.out.println("-----------Deletar usuário-----------");
        visualizar();
        boolean sair = false;
        while(!sair){
            System.out.print("\u001B[36mDigite o ID do usuário que deseja deletar:\u001B[0m ");

            String entrada = sc.nextLine();
            try{
                int id = Integer.parseInt(entrada);

                boolean encontrou = false;
                User usuarioEncontrado = null;
                for(User i : usuariosList){
                    if(i.getId() == id){
                        encontrou = true;
                        usuarioEncontrado = i;
                        sair = true;
                        break;
                    }
                }
                if(usuarioEncontrado != null){
                    usuariosList.remove(usuarioEncontrado);
                }

                if(!encontrou){
                    System.out.print("\u001B[31mID não encontrado. Deseja sair? [S/N]\u001B[0m ");
                    String escolha = String.valueOf(sc.nextLine().trim().toUpperCase().charAt(0));
                    if(escolha.equals("S")){
                        sair = true;
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("Digite um ID válido!");
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
            System.out.println("[4] Deletar usuário");
            System.out.println("[5] Finalizar programa");
            System.out.println("--------------------------");
            System.out.print("DIGITE A OPÇÃO DESEJADA: ");
            int opcao = 0;
            String entrada  = sc.nextLine();

            while(true){
                try{
                    opcao = Integer.parseInt(entrada);

                    if(opcao == 1 || opcao == 2 || opcao == 3 || opcao == 4 || opcao == 5){
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
                    deletar();
                    break;
                case 5:
                    ficar = false;
                    System.out.println("\u001B[36mFinalizando programa...\u001B[0m");
                    break;
            }

        }
    }
    //-----------------------------METODOS UTILITARIOS--------------------------------------

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
