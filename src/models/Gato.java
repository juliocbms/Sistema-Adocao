package models;

public class Gato extends Animal {
    private boolean isCastrado;

    public Gato(String id, String nome, int idade, double peso, String sexo, boolean isCastrado) {
        super(id, nome, idade, peso, sexo);
        this.isCastrado = isCastrado;
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " mia: Miau!");
    }

    @Override
    public void vacinar() {
        System.out.println(getNome() + " foi vacinado contra raiva e V4.");
    }

    @Override
    public void vermifugar() {
        System.out.println(getNome() + " recebeu vermífugo para gatos.");
    }

    @Override
    public String toString() {
        return "Gato [ID=" + id + ", Nome=" + nome + ", Castrado=" + (isCastrado ? "Sim" : "Não") + ", Status=" + status + "]";
    }
}
