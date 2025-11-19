package repository;

import models.Adotante;
import models.Animal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AdotanteRepository {

    private static final String ARQUIVO_DB = "adotantes.csv";
    private AnimalRepository animalRepository;

    public AdotanteRepository() {
        this.animalRepository = new AnimalRepository();
        try {
            File file = new File(ARQUIVO_DB);
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.err.println("Erro ao criar banco de dados de adotantes: " + e.getMessage());
        }
    }

    public void salvar(Adotante adotante) {
        List<Adotante> adotantes = listarTodos();
        boolean existe = false;

        for (int i = 0; i < adotantes.size(); i++) {
            if (adotantes.get(i).getId().equals(adotante.getId())) {
                adotantes.set(i, adotante);
                existe = true;
                break;
            }
        }

        if (!existe) adotantes.add(adotante);
        reescreverArquivo(adotantes);
    }

    public Adotante buscarPorId(String id) {
        for (Adotante a : listarTodos()) {
            if (a.getId().equals(id)) return a;
        }
        return null;
    }

    public List<Adotante> listarTodos() {
        List<Adotante> adotantes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ARQUIVO_DB))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";");
                if (partes.length < 6) continue;

                String id = partes[0];
                String nome = partes[1];
                String email = partes[2];
                String idade = partes[3];
                String cidade = partes[4];
                String sexo = partes[5];

                Adotante adotante = new Adotante(id, nome, email, idade, cidade, sexo);


                if (partes.length > 6 && !partes[6].isEmpty()) {
                    String[] idsAnimais = partes[6].split("\\|");
                    for (String idAnimal : idsAnimais) {
                        Animal animal = animalRepository.buscarPorId(idAnimal);
                        if (animal != null) {
                            adotante.adicionarAnimal(animal);
                        }
                    }
                }
                adotantes.add(adotante);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler adotantes: " + e.getMessage());
        }
        return adotantes;
    }

    private void reescreverArquivo(List<Adotante> adotantes) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ARQUIVO_DB))) {
            for (Adotante a : adotantes) {
                StringBuilder sb = new StringBuilder();
                sb.append(a.getId()).append(";");
                sb.append(a.getNome()).append(";");
                sb.append(a.getEmail()).append(";");
                sb.append(a.getIdade()).append(";");
                sb.append(a.getCidade()).append(";");
                sb.append(a.getSexo()).append(";");


                List<Animal> animais = a.getAnimaisAdotados();
                if (!animais.isEmpty()) {
                    for (int i = 0; i < animais.size(); i++) {
                        sb.append(animais.get(i).getId());
                        if (i < animais.size() - 1) sb.append("|");
                    }
                }

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar adotantes: " + e.getMessage());
        }
    }

    public void atualizar(Adotante adotanteAtualizado) {
        List<Adotante> adotantes = listarTodos();
        boolean encontrado = false;

        for (int i = 0; i < adotantes.size(); i++) {
            if (adotantes.get(i).getId().equals(adotanteAtualizado.getId())) {
                adotantes.set(i, adotanteAtualizado);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            reescreverArquivo(adotantes);
            System.out.println("Adotante atualizado com sucesso!");
        } else {
            System.out.println("Adotante não encontrado para atualização.");
        }
    }

    public void excluir(String id) {
        List<Adotante> adotantes = listarTodos();

        boolean removido = adotantes.removeIf(adotante -> adotante.getId().equals(id));

        if (removido) {
            reescreverArquivo(adotantes);
            System.out.println("Adotante excluído com sucesso!");
        } else {
            System.out.println("Adotante não encontrado com ID: " + id);
        }
    }
}
