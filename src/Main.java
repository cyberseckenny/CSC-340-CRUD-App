import com.sun.tools.jconsole.JConsoleContext;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static DataHandler dataHandler;

    public static void main(String[] args) {
        dataHandler = new DataHandler("data.txt");
        consoleLoop();
    }

    public static void consoleLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1: New data");
            System.out.println("2: Delete data");
            System.out.println("3: Update data");
            System.out.println("4: Print data");

            System.out.println("What is your choice? ");
            try {
                int choice = scanner.nextInt();
                takeChoice(choice, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Choice must be an integer.");
                scanner.next();
            }
        }
    }

    public static void takeChoice(int choice, Scanner scanner) {
        System.out.println();
        scanner.nextLine(); // java is annoying

        String name;
        int age;

        switch (choice) {
            case 1:
                System.out.println("Pick a name to add: ");
                name = scanner.nextLine();

                System.out.println("Pick an age: ");

                try {
                    age = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Age must be an integer.");
                    scanner.next();
                    break;
                }

                if (name.isBlank()) {
                    System.out.println("Name cannot be blank.");
                    break;
                }

                dataHandler.newData(name, age);

                System.out.println("Added entry - " + name + ": " + age);

                break;
            case 2:
                System.out.println("Pick a name to delete: ");
                name = scanner.nextLine();

                if (name.isBlank()) {
                    System.out.println("Name cannot be blank.");
                    break;
                }

                if (dataHandler.deleteData(name)) {
                    System.out.println("Deleted entry - " + name);
                } else {
                    System.out.println("Could not find entry - " + name);
                }

                break;
            case 3:
                System.out.println("Pick a name to update: ");
                name = scanner.nextLine();

                System.out.println("Pick a new name (empty if unchanging): ");
                String newName = scanner.nextLine();

                System.out.println("Pick a new age: ");

                try {
                    age = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Age must be an integer.");
                    scanner.next();
                    break;
                }

                if (name.isBlank()) {
                    System.out.println("Name cannot be blank.");
                    break;
                }

                if (newName.isEmpty()) {
                    newName = name;
                }

                if (dataHandler.updateData(name, newName, age)) {
                    System.out.println("Updated entry - " + newName + ": " + age);
                } else {
                    System.out.println("Could not find entry - " + name);
                }

                break;
            case 4:
                for (String line : dataHandler.getData()) {
                    System.out.println(line);
                }

                break;
            default:
                System.out.println("That is not a valid choice.");
                break;
        }

        System.out.println();
    }
}