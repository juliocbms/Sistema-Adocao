package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adotante {
    private String id;
    private String nome;
    private String email;
    private String idade;
    private String cidade;
    private String sexo;
    private List<Animal> animaisAdotados;


    public Adotante() {
        this.animaisAdotados = new ArrayList<>();
    }


    public Adotante(String id, String nome, String email, String idade, String cidade, String sexo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.cidade = cidade;
        this.sexo = sexo;
        this.animaisAdotados = new ArrayList<>();
    }

    public void adicionarAnimal(Animal animal) {
        this.animaisAdotados.add(animal);
    }


    public List<Animal> getAnimaisAdotados() {
        return Collections.unmodifiableList(animaisAdotados);
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAnimaisAdotados(List<Animal> animaisAdotados) {
        this.animaisAdotados = animaisAdotados;
    }

    @Override
    public String toString() {
        return "Adotante [ID=" + id + ", Nome=" + nome + ", Email=" + email + ", Animais=" + animaisAdotados.size() + "]";
    }
}
