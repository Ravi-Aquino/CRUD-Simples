package Entities;

public class User {
    private String usuario;
    private int idade;
    private String senha;
//--------------------------------------------------------------
    public User(String usuario, int idade, String senha) {
        this.usuario = usuario;
        this.idade = idade;
        this.senha = senha;
    }

    public User() {
    }
    //--------------------------------------------------------------

    public String getusuario() {
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
                "usuario='" + usuario + '\'' +
                ", idade=" + idade +
                ", senha='" + senha + '\'' +
                '}';
    }
}
