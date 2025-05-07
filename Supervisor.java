//Κλάση Supervisor, αναπαριστά τον υπέυθυνο διαχειριστή ενός έργου
public class Supervisor {
    // Ιδιωτικά πεδία για τα στοιχεία του διαχειριστή
    private String firstName;
    private String lastName;
    private String id;

    // Constructor του Supervisor
    public Supervisor(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }
    // Μετροπή σε αρχείο CSV
    public String toCSV() {
        return firstName + "," + lastName + "," + id;
    }

    //Εμφάνιση των στοιχείων του διαχειριστή
    public void displaySupervisorDetails() {
        System.out.println("\nSupervisor: ");
        System.out.println("Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("ID: " + id);
    }

    // Προαιρετικά Getters 
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }
}