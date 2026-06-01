package Entities;

public class User {
    private int id;
    private String usuario;
    private int idade;
    private String senha;
    private static int proximoId = 1;
//--------------------------------------------------------------
    public User() {
        this.id = proximoId;
        proximoId++;
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
