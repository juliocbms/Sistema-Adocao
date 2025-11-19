package repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QuestionRepository {

    private static final String PATH_DIR = "src/data";
    private static final String FILE_MENU = PATH_DIR + "/menu.txt";
    private static final String FILE_FORM_ADOTANTE = PATH_DIR + "/form_adotante.txt";
    private static final String FILE_FORM_ANIMAL = PATH_DIR + "/form_animal.txt";

    public QuestionRepository() {
        createDirectory();
        createMenuFile();
        createAdotanteQuestionsFile();
        createAnimalQuestionsFile();
    }


    private void createDirectory() {
        try {
            Files.createDirectories(Paths.get(PATH_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar diretório: " + e.getMessage());
        }
    }

    private void createMenuFile() {
        File file = new File(FILE_MENU);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("\n=== SISTEMA DE ADOÇÃO ===\n");
                bw.write("1. Cadastrar Adotante\n");
                bw.write("2. Cadastrar Cachorro\n");
                bw.write("3. Cadastrar Gato\n");
                bw.write("4. Realizar Adoção\n");
                bw.write("5. Listar Adotantes\n");
                bw.write("6. Listar Animais Disponíveis\n");
                bw.write("7. Listar Todas Adoções\n");
                bw.write("8. Excluir Registros\n");
                bw.write("9. Editar Registros\n");
                bw.write("10. Cuidados\n");
                bw.write("0. Sair\n");
                bw.write("Opção: ");
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar menu: " + e.getMessage());
            }
        }
    }

    private void createAdotanteQuestionsFile() {
        File file = new File(FILE_FORM_ADOTANTE);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

                bw.write("Nome do Adotante: \n");
                bw.write("E-mail: \n");
                bw.write("Idade: \n");
                bw.write("Cidade: \n");
                bw.write("Sexo: ");
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar form adotante: " + e.getMessage());
            }
        }
    }

    private void createAnimalQuestionsFile() {
        File file = new File(FILE_FORM_ANIMAL);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("Nome do Animal: \n");
                bw.write("Raça: \n");
                bw.write("Idade (anos): \n");
                bw.write("Peso (kg): \n");
                bw.write("Sexo: \n");
                bw.write("É castrado? (true/false): ");
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar form animal: " + e.getMessage());
            }
        }
    }


    public void printMenu() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_MENU))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler menu: " + e.getMessage());
        }
    }


    public void askQuestion(int type, int lineNumber) {
        String filePath = (type == 1) ? FILE_FORM_ADOTANTE : FILE_FORM_ANIMAL;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int cont = 1;
            while ((linha = br.readLine()) != null) {
                if (cont == lineNumber) {
                    System.out.print(linha);
                    return;
                }
                cont++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler pergunta: " + e.getMessage());
        }
    }
}