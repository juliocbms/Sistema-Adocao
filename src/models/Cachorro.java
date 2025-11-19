package models;

public class Cachorro extends Animal {
    private String raca;

    public Cachorro(String id, String nome, int idade, double peso, String sexo, String raca) {
        super(id, nome, idade, peso, sexo);
        this.raca = raca;
    }

    @Override
    public void emitirSom() {
        System.out.println(getNome() + " late: Au Au!");
    }

    @Override
    public void vacinar() {
        System.out.println(getNome() + " foi vacinado contra raiva e V10.");
    }

    @Override
    public void vermifugar() {
        System.out.println(getNome() + " recebeu vermífugo para cães.");
    }

    @Override
    public String toString() {
        return "Cachorro [ID=" + id + ", Nome=" + nome + ", Raça=" + raca + ", Status=" + status + "]";
    }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
}
