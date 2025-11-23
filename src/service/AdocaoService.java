package service;

import exceptions.AnimalIndisponivelException;
import exceptions.LimiteAdocoesException;
import models.Adocao;
import models.Adotante;
import models.Animal;
import models.StatusAnimal;
import repository.AdocaoRepository;
import repository.AdotanteRepository;
import repository.AnimalRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AdocaoService {

    private AdocaoRepository adocaoRepository;
    private AdotanteRepository adotanteRepository;
    private AnimalRepository animalRepository;

    public AdocaoService() {
        this.adocaoRepository = new AdocaoRepository();
        this.adotanteRepository = new AdotanteRepository();
        this.animalRepository = new AnimalRepository();
    }

    public List<Adocao> filtrarPorAdotante(String nomeParcial) {
        return adocaoRepository.listarTodas().stream()
                .filter(a -> a.getAdotante().getNome().toLowerCase().contains(nomeParcial.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Adocao> filtrarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return adocaoRepository.listarTodas().stream()
                .filter(a -> {
                    LocalDateTime data = a.getDataAdocao();
                    return (data.isEqual(inicio) || data.isAfter(inicio)) &&
                            (data.isEqual(fim) || data.isBefore(fim));
                })
                .collect(Collectors.toList());
    }

    public void realizarAdocao(String idAdotante, String idAnimal) throws LimiteAdocoesException, AnimalIndisponivelException {
        Adotante adotante = adotanteRepository.buscarPorId(idAdotante);
        Animal animal = animalRepository.buscarPorId(idAnimal);

        if (adotante == null) {
            throw new IllegalArgumentException("Adotante não encontrado com ID: " + idAdotante);
        }

        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado com ID: " + idAnimal);
        }

        if (adotante.getAnimaisAdotados().size() >= 3) {
            throw new LimiteAdocoesException("O adotante " + adotante.getNome() + " já atingiu o limite máximo de 3 adoções.");
        }

        if (animal.getStatus() != StatusAnimal.DISPONIVEL) {
            throw new AnimalIndisponivelException("O animal " + animal.getNome() + " não está disponível para adoção.");
        }

        Adocao novaAdocao = new Adocao(adotante, animal);
        adocaoRepository.salvar(novaAdocao);

        animal.setStatus(StatusAnimal.ADOTADO);
        animalRepository.salvar(animal);

        adotante.adicionarAnimal(animal);
        adotanteRepository.salvar(adotante);
    }

    public List<Adocao> listarAdocoes() {
        return adocaoRepository.listarTodas();
    }
}