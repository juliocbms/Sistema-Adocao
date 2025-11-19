package repository;

import models.Animal;
import models.Cachorro;
import models.Gato;
import models.StatusAnimal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    private static final String ARQUIVO_DB = "animais.csv";

    public AnimalRepository() {
        try {
            File file = new File(ARQUIVO_DB);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar banco de dados de animais: " + e.getMessage());
        }
    }

    public void salvar(Animal animal) {
        List<Animal> animais = listarTodos();
        boolean existe = false;

        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId().equals(animal.getId())) {
                animais.set(i, animal);
                existe = true;
                break;
            }
        }

        if (!existe) {
            animais.add(animal);
        }

        reescreverArquivo(animais);
    }

    public Animal buscarPorId(String id) {
        List<Animal> animais = listarTodos();
        for (Animal a : animais) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public List<Animal> listarTodos() {
        List<Animal> animais = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ARQUIVO_DB))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";");

                if (partes.length < 8) continue;

                String tipo = partes[0];
                String id = partes[1];
                String nome = partes[2];
                int idade = Integer.parseInt(partes[3]);
                double peso = Double.parseDouble(partes[4]);
                String sexo = partes[5];
                StatusAnimal status = StatusAnimal.valueOf(partes[6]);
                String extra = partes[7];

                Animal animal;
                if (tipo.equals("CACHORRO")) {
                    animal = new Cachorro(id, nome, idade, peso, sexo, extra);
                } else {
                    animal = new Gato(id, nome, idade, peso, sexo, Boolean.parseBoolean(extra));
                }
                animal.setStatus(status);
                animais.add(animal);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler animais: " + e.getMessage());
        }
        return animais;
    }

    private void reescreverArquivo(List<Animal> animais) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ARQUIVO_DB))) {
            for (Animal a : animais) {
                StringBuilder sb = new StringBuilder();

                if (a instanceof Cachorro) {
                    sb.append("CACHORRO;");
                    sb.append(a.getId()).append(";");
                    sb.append(a.getNome()).append(";");
                    sb.append(a.getIdade()).append(";");
                    sb.append(a.getPeso()).append(";");
                    sb.append(a.getSexo()).append(";");
                    sb.append(a.getStatus()).append(";");
                    sb.append(((Cachorro) a).getRaca());
                } else if (a instanceof Gato) {
                    sb.append("GATO;");
                    sb.append(a.getId()).append(";");
                    sb.append(a.getNome()).append(";");
                    sb.append(a.getIdade()).append(";");
                    sb.append(a.getPeso()).append(";");
                    sb.append(a.getSexo()).append(";");
                    sb.append(a.getStatus()).append(";");

                    sb.append("true");
                }

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar animais: " + e.getMessage());
        }
    }

    public void atualizar(Animal animalAtualizado) {
        List<Animal> animais = listarTodos();
        boolean encontrado = false;

        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId().equals(animalAtualizado.getId())) {
                animais.set(i, animalAtualizado);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            reescreverArquivo(animais);
            System.out.println("Animal atualizado com sucesso!");
        } else {
            System.out.println("Animal não encontrado para atualização.");
        }
    }

    public void excluir(String id) {
        List<Animal> animais = listarTodos();
        boolean removido = animais.removeIf(animal -> animal.getId().equals(id));

        if (removido) {
            reescreverArquivo(animais);
            System.out.println("Animal excluído com sucesso!");
        } else {
            System.out.println("Animal não encontrado com ID: " + id);
        }
    }
}
