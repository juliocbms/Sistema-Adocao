package models;

public abstract class Animal implements CuidadosEspeciais {
    protected String id;
    protected String nome;
    protected int idade;
    protected double peso;
    protected String sexo;
    protected StatusAnimal status;

    public Animal(String id, String nome, int idade, double peso, String sexo) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.sexo = sexo;
        this.status = StatusAnimal.DISPONIVEL;
    }


    public abstract void emitirSom();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public StatusAnimal getStatus() {
        return status;
    }

    public void setStatus(StatusAnimal status) {
        this.status = status;
    }
}
