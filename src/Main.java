import br.com.dio.dao.UserDAO;
import br.com.dio.model.MenuOption;
import br.com.dio.model.UserModel;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static UserDAO dao = new UserDAO();

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Bem vindo ao cadastro de usuários, selecione a operação desejada:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar por identificador");
            System.out.println("5 - Listar");
            System.out.println("6 - Sair");

            var userInput = scanner.nextInt();

            var selectedOption = MenuOption.values()[userInput - 1];
            switch (selectedOption) {
                case SAVE -> {
                    var user = dao.save(requestToSave());
                    System.out.printf("Usuário cadastrado com sucesso %s", user);
                }
                case UPDATE -> {
                    var user = dao.update(requestToUpdate());
                    System.out.printf("Usuário atualizado com sucesso %s", user);
                }
                case DELETE -> {
                    dao.delete(requestId());
                    System.out.println("Usuário deletado com sucesso");
                }
                case FIND_BY_ID -> {
                    var idRequest = requestId();
                    var users = dao.findById(idRequest);
                    System.out.printf("Usuário com id %s", idRequest);
                    System.out.println(users);
                }
                case FIND_ALL -> {
                    var users = dao.findAll();
                    System.out.println("Usuários Cadastrados");
                    System.out.println("====================");
                    users.forEach(System.out::println);
                    System.out.println("====================");
                }
                case EXIT -> System.exit(0);
                default -> throw new IllegalStateException("Unexpected value: " + selectedOption);
            }
        }

    }

    private static long requestId(){
        System.out.println("Informe o identificador do usuário: ");
        return scanner.nextLong();

    }

    private static UserModel requestToSave(){
        System.out.println("Informe o nome do usuário: ");
        var name = scanner.next();
        System.out.println("Informe o email do usuário: ");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento do usuário: (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = OffsetDateTime.parse(birthdayString, formatter);

        return new UserModel(0, name, email, birthday);
    }

    private static UserModel requestToUpdate(){
        System.out.println("Informe o identificador do usuário: ");
        var id = scanner.nextLong();
        System.out.println("Informe o nome do usuário: ");
        var name = scanner.next();
        System.out.println("Informe o email do usuário: ");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento do usuário: (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = OffsetDateTime.parse(birthdayString, formatter);

        return new UserModel(id, name, email, birthday);
    }
}