import repository.QuestionRepository;
import java.util.Scanner;
import service.ui.AdocaoUI;
import service.ui.AdotanteUI;
import service.ui.AnimalUI;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        QuestionRepository questionRepository = new QuestionRepository();


        AnimalUI animalUI = new AnimalUI(sc);
        AdotanteUI adotanteUI = new AdotanteUI(sc);
        AdocaoUI adocaoUI = new AdocaoUI(sc);

        while (true) {

            questionRepository.printMenu();


            String input = sc.nextLine();
            int escolhaPrincipal;

            try {
                escolhaPrincipal = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            switch (escolhaPrincipal) {
                case 1:
                    adotanteUI.cadastrar();
                    break;

                case 2:
                    animalUI.cadastrarCachorro();
                    break;

                case 3:
                    animalUI.cadastrarGato();
                    break;

                case 4:
                    adocaoUI.realizarAdocao();
                    break;

                case 5:
                    adotanteUI.listar();
                    break;

                case 6:
                    animalUI.listar();
                    break;

                case 7:
                    adocaoUI.listarAdocoes();
                    break;

                case 8:
                    adocaoUI.menuRelatorios();
                    break;

                case 9:
                    System.out.println("1 - Excluir Animal | 2 - Excluir Adotante");
                    String sub = sc.nextLine();
                    if (sub.equals("1")) {
                        animalUI.excluir();
                    } else if (sub.equals("2")) {
                        adotanteUI.excluir();
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 10:
                    System.out.println("\n--- Menu Edição ---");
                    System.out.println("1 - Editar Animal");
                    System.out.println("2 - Editar Adotante");
                    System.out.print("Escolha: ");
                    String subEdit = sc.nextLine();

                    if (subEdit.equals("1")) {
                        animalUI.editar();
                    } else if (subEdit.equals("2")) {
                        adotanteUI.editar();
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 11:
                    animalUI.administrarCuidados();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida! Digite uma opção do menu.");
            }
        }
    }
}