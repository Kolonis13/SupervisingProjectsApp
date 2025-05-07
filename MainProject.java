import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MainProject {
    public static final String FILE_NAME = "projects.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Project> projects = loadProjectsFromCSV(FILE_NAME); // Αρχικοποίηση από αρχείο CSV

        int choice;
        do {
            System.out.println("\n--- Project Management Menu ---");
            System.out.println("1. Add new project");
            System.out.println("2. Display all projects");
            System.out.println("3. Assign supervisor to existing project");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    Project newProject = createNewProject(scanner);
                    projects.add(newProject);
                    saveAllProjectsToCSV(projects, FILE_NAME);
                    break;
                case 2:
                    displayProjects(projects);
                    break;
                case 3:
                    assignSupervisorToProject(scanner, projects);
                    saveAllProjectsToCSV(projects, FILE_NAME);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Δημιουργεί ένα νέο έργο με είσοδο χρήστη
    private static Project createNewProject(Scanner scanner) {
        System.out.println("\n--- Add New Project ---");

        String title = getInput(scanner, "Title");
        String startDate = getDateInput(scanner, "Start date (YYYY-MM-DD)");
        String endDate = getDateInput(scanner, "End date (YYYY-MM-DD)");

        System.out.println("\n--- Supervisor Details ---");
        String firstName = getInput(scanner, "First Name");
        String lastName = getInput(scanner, "Last Name");
        String id = getInput(scanner, "ID");

        Supervisor supervisor = new Supervisor(firstName, lastName, id);
        Project project = new Project(title, startDate, endDate, supervisor);

        System.out.println("\nProject added successfully:");
        project.displayProjectDetails();

        return project;
    }

    // Ανάθεση νέου διαχειριστή σε υπάρχον έργο
    private static void assignSupervisorToProject(Scanner scanner, List<Project> projects) {
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
            return;
        }

        System.out.println("\n--- Select a Project ---");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getTitle());
        }

        int choice;
        do {
            System.out.print("Enter project number (1-" + projects.size() + "): ");
            choice = getIntInput(scanner);
        } while (choice < 1 || choice > projects.size());

        Project selectedProject = projects.get(choice - 1);

        System.out.println("\n--- New Supervisor Details ---");
        String firstName = getInput(scanner, "First Name");
        String lastName = getInput(scanner, "Last Name");
        String id = getInput(scanner, "ID");

        Supervisor newSupervisor = new Supervisor(firstName, lastName, id);
        selectedProject.assignSupervisor(newSupervisor);

        System.out.println("Supervisor assigned successfully.");
    }

    // Εμφανίζει όλα τα έργα
    private static void displayProjects(List<Project> projects) {
        if (projects.isEmpty()) {
            System.out.println("No projects to display.");
            return;
        }

        System.out.println("\n--- All Projects ---");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("\nProject #" + (i + 1));
            projects.get(i).displayProjectDetails();
        }
    }

    // Αποθήκευση όλων των έργων σε αρχείο CSV
    private static void saveAllProjectsToCSV(List<Project> projects, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Project project : projects) {
                writer.write(project.toCSV() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Φόρτωση έργων από CSV (αν υπάρχει)
    private static List<Project> loadProjectsFromCSV(String filename) {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Supervisor sup = new Supervisor(parts[3], parts[4], parts[5]);
                    Project project = new Project(parts[0], parts[1], parts[2], sup);
                    projects.add(project);
                }
            }
        } catch (FileNotFoundException e) {
            // Δεν κάνουμε τίποτα αν δεν υπάρχει το αρχείο, ξεκινάμε με άδειο
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return projects;
    }

    // Διασφαλίζει μη κενές απαντήσεις
    private static String getInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    // Επαλήθευση ημερομηνίας μορφής YYYY-MM-DD
    private static String getDateInput(Scanner scanner, String prompt) {
        String date;
        do {
            System.out.print(prompt + ": ");
            date = scanner.nextLine().trim();
        } while (!isValidDate(date));
        return date;
    }

    private static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Εισαγωγή ακεραίου με έλεγχο
    private static int getIntInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
