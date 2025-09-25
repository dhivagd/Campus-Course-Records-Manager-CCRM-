
import java.util.*;

// ===== Enum for Schools =====
enum School {
    SCSE("Computer Science & Engineering"),
    SBS("Biosciences"),
    SENSE("Electronics & Electrical Engineering"),
    SCHM("Humanities & Management");

    private final String fullName;
    School(String fullName) { this.fullName = fullName; }
    public String getFullName() { return fullName; }
}

// ===== Student Class =====
class Student {
    private final String regNo;
    private final String name;
    private final String program;
    private final School school;

    public Student(String regNo, String name, String program, School school) {
        this.regNo = regNo;
        this.name = name;
        this.program = program;
        this.school = school;
    }

    public String getRegNo() { return regNo; }
    public String getName() { return name; }
    public String getProgram() { return program; }
    public School getSchool() { return school; }

    @Override
    public String toString() {
        return regNo + " - " + name + " (" + program + ", " + school.getFullName() + ")";
    }
}

// ===== Faculty Class =====
class Faculty {
    private final String name;
    private final String subject;

    public Faculty(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return name + " — " + subject;
    }
}

// ===== Course Class =====
class Course {
    private final String code;
    private final String title;
    private final int credits;
    private final Faculty faculty;

    public Course(String code, String title, int credits, Faculty faculty) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return code + ": " + title + " (" + credits + " credits, Faculty: " + faculty + ")";
    }
}

// ===== Main Menu-Driven System =====
public class VITBhopalCCRM {
    private static final List<Student> students = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Faculty> facultyList = new ArrayList<>();

    public static void main(String[] args) {
        initSampleData();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== VIT BHOPAL CCRM MENU =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. View Faculty");
            System.out.println("4. Reports");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": studentMenu(sc); break;
                case "2": courseMenu(sc); break;
                case "3": viewFaculty(); break;
                case "4": reports(); break;
                case "0": running = false; System.out.println("Exiting CCRM. Goodbye!"); break;
                default: System.out.println("Invalid choice."); 
            }
        }
        sc.close();
    }

    // ===== Sample Data =====
    private static void initSampleData() {
        // Required students
        students.add(new Student("23BCY10781", "Dhivagar Pakkirisamy", "B.Tech Biotechnology", School.SBS));
        students.add(new Student("23BCY10223", "R Manoj Kumar", "B.Tech Biotechnology", School.SBS));
        // Extra students
        students.add(new Student("23BCS1001", "Priya Sharma", "B.Tech CSE", School.SCSE));
        students.add(new Student("23BEC1002", "Amit Kumar", "B.Tech ECE", School.SENSE));

        // Faculty (required)
        Faculty ferroz = new Faculty("Ferroz Babu", "Mathematics");
        Faculty suku = new Faculty("Sukumar", "AI / ML");
        Faculty suresh = new Faculty("M Suresh", "DSA");
        facultyList.addAll(Arrays.asList(ferroz, suku, suresh));

        // Courses
        courses.add(new Course("MAT101", "Engineering Mathematics", 3, ferroz));
        courses.add(new Course("AIML101", "Intro to AI/ML", 3, suku));
        courses.add(new Course("DSA101", "Data Structures", 4, suresh));
    }

    // ===== Student Menu =====
    private static void studentMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. View All Students");
            System.out.println("2. Search Student");
            System.out.println("3. Add Student");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            switch (c) {
                case "1": viewStudents(); break;
                case "2": searchStudent(sc); break;
                case "3": addStudent(sc); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewStudents() {
        System.out.println("\n=== STUDENT DIRECTORY ===");
        if (students.isEmpty()) { System.out.println("No students found."); return; }
        students.forEach(System.out::println);
    }

    private static void searchStudent(Scanner sc) {
        System.out.print("Enter name or reg no: ");
        String key = sc.nextLine().toLowerCase();
        students.stream()
            .filter(s -> s.getName().toLowerCase().contains(key) || s.getRegNo().contains(key))
            .forEach(System.out::println);
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Enter Reg No: ");
        String regNo = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Program: ");
        String program = sc.nextLine();
        System.out.println("Choose School: 1.SCSE 2.SBS 3.SENSE 4.SCHM");
        int s = Integer.parseInt(sc.nextLine());
        School school = School.values()[s-1];
        students.add(new Student(regNo, name, program, school));
        System.out.println("Student added successfully!");
    }

    // ===== Course Menu =====
    private static void courseMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- COURSE MENU ---");
            System.out.println("1. View Courses");
            System.out.println("2. Add Course");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            switch (c) {
                case "1": viewCourses(); break;
                case "2": addCourse(sc); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewCourses() {
        System.out.println("\n=== COURSE LIST ===");
        if (courses.isEmpty()) { System.out.println("No courses available."); return; }
        courses.forEach(System.out::println);
    }

    private static void addCourse(Scanner sc) {
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Credits: ");
        int credits = Integer.parseInt(sc.nextLine());
        System.out.println("Choose Faculty:");
        for (int i=0;i<facultyList.size();i++) {
            System.out.println((i+1)+". "+facultyList.get(i));
        }
        int f = Integer.parseInt(sc.nextLine());
        Faculty fac = facultyList.get(f-1);
        courses.add(new Course(code, title, credits, fac));
        System.out.println("Course added!");
    }

    // ===== Faculty =====
    private static void viewFaculty() {
        System.out.println("\n=== FACULTY LIST ===");
        facultyList.forEach(System.out::println);
    }

    // ===== Reports =====
    private static void reports() {
        System.out.println("\n=== REPORTS ===");
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Courses: " + courses.size());
        System.out.println("Total Faculty: " + facultyList.size());

        Map<School, Long> schoolCount = new HashMap<>();
        for (Student s : students) {
            schoolCount.put(s.getSchool(), schoolCount.getOrDefault(s.getSchool(), 0L)+1);
        }
        System.out.println("\nStudents by School:");
        schoolCount.forEach((sch, count) -> System.out.println(sch.getFullName() + ": " + count));
    }
}
