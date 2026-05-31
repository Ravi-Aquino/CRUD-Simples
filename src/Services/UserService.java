package Services;

import Entities.User;
//
import java.util.Arrays;
import java.util.Scanner;

public class UserService {
    static Scanner sc = new Scanner(System.in);
    //-------------------------------------------------------------
    public static void verificarArquivo(){
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

                    if(opcao == 1 || opcao == 2){
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
                    ficar = false;
                    System.out.println("Finalizando programa...");
                    break;
            }
        }
    }
    //----------------------METHODS---------------------------------------
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
