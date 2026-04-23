package Assignments;
import java.io.*;
import java.util.Scanner;

public class Assignment9 {

    static final String FILE_NAME = "students.txt";

    // Create file if not exists
    static void createFileIfNotExists() {
        try {
            File file = new File(FILE_NAME);
            if (file.createNewFile()) {
                System.out.println("File created: " + FILE_NAME);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file.");
        }
    }

    // Write or Append data
    static void writeData(boolean append) {
        try (FileWriter fw = new FileWriter(FILE_NAME, append);
             BufferedWriter bw = new BufferedWriter(fw);
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Grade: ");
            String grade = sc.nextLine();

            bw.write(name + "," + id + "," + grade);
            bw.newLine();

            System.out.println("Record saved successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    // Read and Display data
    static void readData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Student Records ---");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("Name: " + data[0] + 
                                   ", ID: " + data[1] + 
                                   ", Grade: " + data[2]);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        createFileIfNotExists();

        while (true) {
            System.out.println("\n1. Overwrite Data");
            System.out.println("2. Append Data");
            System.out.println("3. Display Records");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    writeData(false); // overwrite
                    break;
                case 2:
                    writeData(true); // append
                    break;
                case 3:
                    readData();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

