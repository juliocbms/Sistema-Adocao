package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adocao {
    private Adotante adotante;
    private Animal animal;
    private LocalDateTime dataAdocao;

    public Adocao(Adotante adotante, Animal animal,LocalDateTime dataAdocao) {
        this.adotante = adotante;
        this.animal = animal;
        this.dataAdocao = LocalDateTime.now();
    }

    public Adocao(Adotante adotante, Animal animal) {
        this.adotante = adotante;
        this.animal = animal;
        this.dataAdocao = LocalDateTime.now();
    }

    public Adotante getAdotante() { return adotante; }
    public Animal getAnimal() { return animal; }
    public LocalDateTime getDataAdocao() { return dataAdocao; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "=== Registro de Adoção ===\n" +
                "Data: " + dataAdocao.format(fmt) + "\n" +
                "Adotante: " + adotante.getNome() + "\n" +
                "Animal: " + animal.getNome() + " (" + animal.getClass().getSimpleName() + ")";
    }
}