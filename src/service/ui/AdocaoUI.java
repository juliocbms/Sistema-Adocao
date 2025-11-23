package service.ui;

import models.Adocao;
import models.Animal;
import models.StatusAnimal;
import repository.AdotanteRepository;
import repository.AnimalRepository;
import service.AdocaoService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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

    public void menuRelatorios() {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Listar Todas");
        System.out.println("2. Filtrar por Nome do Adotante");
        System.out.println("3. Filtrar por Período (Datas)");
        System.out.print("Escolha: ");

        String op = scanner.nextLine();
        List<Adocao> resultados = List.of();

        switch (op) {
            case "1" -> resultados = service.listarAdocoes();

            case "2" -> {
                System.out.print("Digite parte do nome do adotante: ");
                String nome = scanner.nextLine();
                resultados = service.filtrarPorAdotante(nome);
            }

            case "3" -> {
                try {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    System.out.print("Data inicial (dd/mm/aaaa): ");
                    LocalDate inicioDate = LocalDate.parse(scanner.nextLine(), fmt);
                    LocalDateTime inicio = inicioDate.atStartOfDay();

                    System.out.print("Data final (dd/mm/aaaa): ");
                    LocalDate fimDate = LocalDate.parse(scanner.nextLine(), fmt);
                    LocalDateTime fim = fimDate.atTime(LocalTime.MAX);


                    resultados = service.filtrarPorPeriodo(inicio, fim);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido! Use dia/mês/ano.");
                    return;
                }
            }
            default -> System.out.println("Opção inválida.");
        }

        if (resultados.isEmpty()) {
            System.out.println("Nenhuma adoção encontrada com esses critérios.");
        } else {
            System.out.println("\n--- Resultados Encontrados ---");
            resultados.forEach(System.out::println);
        }
    }

    public void listarAdocoes() {
        System.out.println("\n>> Histórico");
        service.listarAdocoes().forEach(System.out::println);
    }
}
