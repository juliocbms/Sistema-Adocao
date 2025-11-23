package repository;

import models.Adocao;
import models.Adotante;
import models.Animal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdocaoRepository {

    private static final String ARQUIVO_DB = "adocoes.csv";
    private AdotanteRepository adotanteRepository;
    private AnimalRepository animalRepository;

    public AdocaoRepository() {
        this.adotanteRepository = new AdotanteRepository();
        this.animalRepository = new AnimalRepository();
        try {
            File file = new File(ARQUIVO_DB);
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.err.println("Erro ao criar banco de adocoes: " + e.getMessage());
        }
    }

    public void salvar(Adocao adocao) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ARQUIVO_DB), StandardOpenOption.APPEND)) {
            String linha = adocao.getDataAdocao().toString() + ";" +
                    adocao.getAdotante().getId() + ";" +
                    adocao.getAnimal().getId();
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao registrar adoção: " + e.getMessage());
        }
    }

    public List<Adocao> listarTodas() {
        List<Adocao> adocoes = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ARQUIVO_DB))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";");
                if (partes.length < 3) continue;

                try {
                    String dataString = partes[0];
                    LocalDateTime dataHistorica = LocalDateTime.parse(dataString);

                    Adotante adotante = adotanteRepository.buscarPorId(partes[1]);
                    Animal animal = animalRepository.buscarPorId(partes[2]);

                    if (adotante != null && animal != null) {
                        adocoes.add(new Adocao(adotante, animal, dataHistorica));
                    }
                } catch (Exception e) {
                    System.err.println("Ignorando linha inválida no histórico: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler adocoes: " + e.getMessage());
        }
        return adocoes;
    }
}
