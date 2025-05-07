// Κλάση Project, αναπαριστά ενα ευρενητικό έργο
public class Project {
    // Ιδιωτικά πεδία για τις βασικές πληροφορίες του έργου
    private String title;
    private String startDate;
    private String endDate;
    private Supervisor supervisor; //Συσχέτιση με την κλάση Supervisor

    //Default constructor, αρχικοποιεί τις μεταβλητές με κενές τιμές
    public Project() {
        this.title = "";
        this.startDate = "";
        this.endDate = "";
        this.supervisor = null;
    }

    // Constructor με παραμέτρους, αρχικοποιεί όλα τα στοιχεία του έρογου
    public Project(String title, String startDate, String endDate, Supervisor supervisor) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.supervisor = supervisor;
    }


    // Μέθοδος ανάθεσης νέου διαχειριστή στο έργο
    public void assignSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    // Εμφάνιση των στοιχείων του έργου και τον διαχειριστή
    public void displayProjectDetails() {
        System.out.println("Title: " + title);
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
  
        if (supervisor != null) {
            supervisor.displaySupervisorDetails();
        }else {
            System.out.println("No supervisor assigned");
        }
    }

    // Μέθοδος για την μετατροπή του έργου σε μορφή CSV
    public String toCSV() {
        return title + "," + startDate + "," + endDate + "," + supervisor.toCSV();
    }
    // Getters για πρόσβαση των στοιχείων από άλλες κλάσεις
    public String getTitle() {
        return title;
    }
    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }
    
}
