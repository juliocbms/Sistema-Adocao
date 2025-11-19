package service.ui;

import models.Adotante;
import repository.AdotanteRepository;
import repository.QuestionRepository;

import java.util.Scanner;
import java.util.UUID;

public class AdotanteUI {
    private final AdotanteRepository repository;
    private final QuestionRepository questions;
    private final Scanner scanner;

    public AdotanteUI(Scanner scanner) {
        this.scanner = scanner;
        this.repository = new AdotanteRepository();
        this.questions = new QuestionRepository();
    }

    public void cadastrar() {
        System.out.println("\n>> Novo Adotante");
        questions.askQuestion(1, 1);
        String nome = scanner.nextLine();
        questions.askQuestion(1, 2);
        String email = scanner.nextLine();
        questions.askQuestion(1, 3);
        String idade = scanner.nextLine();
        questions.askQuestion(1, 4);
        String cidade = scanner.nextLine();
        questions.askQuestion(1, 5);
        String sexo = scanner.nextLine();

        Adotante a = new Adotante(UUID.randomUUID().toString().substring(0, 8), nome, email, idade, cidade, sexo);
        repository.salvar(a);
        System.out.println("Adotante salvo com sucesso! ID: " + a.getId());
    }

    public void listar() {
        System.out.println("\n>> Lista de Adotantes");
        for (Adotante a : repository.listarTodos()) {
            System.out.println(a);
        }
    }

    public void excluir() {
        System.out.println("\n>> Excluir Adotante");
        listar();
        System.out.print("Digite o ID do adotante para excluir: ");
        String id = scanner.nextLine();

        repository.excluir(id);
    }

    public void editar() {
        System.out.println("\n>> Editar Adotante");
        listar();
        System.out.print("Digite o ID do adotante para editar: ");
        String id = scanner.nextLine();

        Adotante adotante = repository.buscarPorId(id);
        if (adotante == null) {
            System.out.println("Adotante não encontrado!");
            return;
        }

        System.out.println("Editando " + adotante.getNome() + ". Pressione ENTER para manter o valor atual.");

        System.out.print("Novo Nome (" + adotante.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) adotante.setNome(nome);

        System.out.print("Novo Email (" + adotante.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) adotante.setEmail(email);

        System.out.print("Nova Cidade (" + adotante.getCidade() + "): ");
        String cidade = scanner.nextLine();
        if (!cidade.isBlank()) adotante.setCidade(cidade);

        repository.atualizar(adotante);
    }
}
