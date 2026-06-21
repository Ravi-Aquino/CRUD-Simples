package Entities;

import java.io.*;

public class User {
    private int id;
    private String usuario;
    private int idade;
    private String senha;
//--------------------------------------------------------------
    static File file = new File("F:\\coisas Ravi\\projetos\\CRUD-Simples\\src\\Repository\\USUARIOS.txt");
    {
        if(!file.exists()){

            FileWriter fw = null;
            try {
                fw = new FileWriter(file,true);
                fw.write("");
                System.out.println("Arquivo criado com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Falha ao encontrar ou criar arquivo");
            }
         }
    }
    public User() {
        if(file.length() == 0){
            this.id = 1;
        }else{
            String lastLine = null;
            try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)){
                String line;
                while((line = br.readLine()) != null){
                    lastLine = line;
                }
                if(lastLine != null && !lastLine.isBlank()){
                    String[] parts = lastLine.split(";");
                    this.id = Integer.parseInt(parts[0].trim()) + 1;
                }else{
                    this.id = 1;
                }
            } catch (IOException e) {
               e.printStackTrace();
            }
        }

    }
    //--------------------------------------------------------------


    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if(idade >= 0  && idade <= 125){
            this.idade = idade;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(senha.length() >= 6){
            this.senha = senha;
        }
    }
    //--------------------------------------------------------------


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", idade=" + idade +
                ", senha='" + senha + '\'' +
                '}';
    }
}
