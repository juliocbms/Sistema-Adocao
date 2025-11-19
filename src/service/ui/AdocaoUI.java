package service.ui;

import models.Animal;
import models.StatusAnimal;
import repository.AdotanteRepository;
import repository.AnimalRepository;
import service.AdocaoService;

import java.util.Scanner;

public class AdocaoUI {
    private final AdocaoService service;
    private final AnimalRepository animalRepo;
    private final AdotanteRepository adotanteRepo;
    private final Scanner scanner;

    public AdocaoUI(Scanner scanner) {
        this.scanner = scanner;
        this.service = new AdocaoService();
        this.animalRepo = new AnimalRepository();
        this.adotanteRepo = new AdotanteRepository();
    }



    public void realizarAdocao() {
        System.out.println("\n>> Nova Adoção");

        System.out.println("--- Adotantes ---");
        adotanteRepo.listarTodos().forEach(System.out::println);
        System.out.print("ID do Adotante: ");
        String idAdotante = scanner.nextLine();

        System.out.println("--- Animais Disponíveis ---");
        for (Animal a : animalRepo.listarTodos()) {
            if (a.getStatus() == StatusAnimal.DISPONIVEL) {
                System.out.println(a);
            }
        }
        System.out.print("ID do Animal: ");
        String idAnimal = scanner.nextLine();

        try {
            service.realizarAdocao(idAdotante, idAnimal);
            System.out.println("Adoção realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarAdocoes() {
        System.out.println("\n>> Histórico");
        service.listarAdocoes().forEach(System.out::println);
    }
}
