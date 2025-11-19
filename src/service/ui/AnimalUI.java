package service.ui;

import models.Animal;
import models.Cachorro;
import models.Gato;
import repository.AnimalRepository;
import repository.QuestionRepository;

import java.util.Scanner;
import java.util.UUID;

public class AnimalUI {
    private final AnimalRepository repository;
    private final QuestionRepository questions;
    private final Scanner scanner;

    public AnimalUI(Scanner scanner) {
        this.scanner = scanner;
        this.repository = new AnimalRepository();
        this.questions = new QuestionRepository();
    }

    public void cadastrarCachorro() {
        System.out.println("\n>> Novo Cachorro");
        try {
            questions.askQuestion(2, 1);
            String nome = scanner.nextLine();

            questions.askQuestion(2, 2);
            String raca = scanner.nextLine();

            questions.askQuestion(2, 3);
            int idade = Integer.parseInt(scanner.nextLine());

            questions.askQuestion(2, 4);
            double peso = Double.parseDouble(scanner.nextLine());

            questions.askQuestion(2, 5);
            String sexo = scanner.nextLine();

            Cachorro c = new Cachorro(UUID.randomUUID().toString().substring(0, 8), nome, idade, peso, sexo, raca);
            repository.salvar(c);
            System.out.println("Cachorro salvo! ID: " + c.getId());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números para idade e peso.");
        }
    }

    public void cadastrarGato() {
        System.out.println("\n>> Novo Gato");
        try {
            questions.askQuestion(2, 1);
            String nome = scanner.nextLine();

            questions.askQuestion(2, 6);
            boolean castrado = Boolean.parseBoolean(scanner.nextLine());

            questions.askQuestion(2, 3);
            int idade = Integer.parseInt(scanner.nextLine());

            questions.askQuestion(2, 4);
            double peso = Double.parseDouble(scanner.nextLine());

            questions.askQuestion(2, 5);
            String sexo = scanner.nextLine();

            Gato g = new Gato(UUID.randomUUID().toString().substring(0, 8), nome, idade, peso, sexo, castrado);
            repository.salvar(g);
            System.out.println("Gato salvo! ID: " + g.getId());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números para idade e peso.");
        }
    }

    public void listar() {
        System.out.println("\n>> Lista de Animais");
        for (Animal a : repository.listarTodos()) {
            System.out.println(a);
        }
    }

    public void excluir() {
        System.out.println("\n>> Excluir Animal");
        listar();
        System.out.print("Digite o ID do animal para excluir: ");
        String id = scanner.nextLine();

        repository.excluir(id);
    }

    public void editar() {
        System.out.println("\n>> Editar Animal");
        listar();
        System.out.print("Digite o ID do animal para editar: ");
        String id = scanner.nextLine();

        Animal animal = repository.buscarPorId(id);
        if (animal == null) {
            System.out.println("Animal não encontrado!");
            return;
        }

        System.out.println("Editando " + animal.getNome() + ". Pressione ENTER para manter o valor atual.");

        System.out.print("Novo Nome (" + animal.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) animal.setNome(nome);

        try {
            System.out.print("Nova Idade (" + animal.getIdade() + "): ");
            String idadeStr = scanner.nextLine();
            if (!idadeStr.isBlank()) animal.setIdade(Integer.parseInt(idadeStr));

            System.out.print("Novo Peso (" + animal.getPeso() + "): ");
            String pesoStr = scanner.nextLine();
            if (!pesoStr.isBlank()) animal.setPeso(Double.parseDouble(pesoStr));


            repository.atualizar(animal);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Dados numéricos inválidos. Atualização cancelada.");
        }
    }


    public void administrarCuidados() {
        System.out.println("\n>> Cuidados Veterinários & Interação");
        listar();
        System.out.print("Digite o ID do animal: ");
        String id = scanner.nextLine();

        Animal animal = repository.buscarPorId(id);

        if (animal != null) {
            System.out.println("\n--- Realizando procedimentos em " + animal.getNome() + " ---");


            System.out.print("Som: ");
            animal.emitirSom();


            System.out.print("Vacinação: ");
            animal.vacinar();

            System.out.print("Vermifugação: ");
            animal.vermifugar();

            System.out.println("----------------------------------------------------");
            System.out.println("Procedimentos realizados com sucesso!");
        } else {
            System.out.println("Animal não encontrado.");
        }
    }
}