package Main;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteApp {

    private static List<Note> notes = new ArrayList<>();
    private static StringBuffer logBuffer = new StringBuffer();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadNotes(); // Load existing notes from file

        System.out.println("📝 Welcome to Note App (CLI)");
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();

        while (true) {
            System.out.println("\n1. Add Note");
            System.out.println("2. View Notes (JSON format)");
            System.out.println("3. Simulate Multi-user Logging");
            System.out.println("4. Delete Note");
            System.out.println("5. Edit Note");
            System.out.println("6. View Note Details");
            System.out.println("7. Search Notes");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            try {
                //scanner.nextLine(); // consume newline
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addNote();
                        break;
                    case 2:
                        viewNotesAsJSON();
                        break;
                    case 3:
                        simulateMultiUserLogging();
                        break;
                    case 4:
                        deleteNote();
                        break;
                    case 5:
                        editNote();
                        break;
                    case 6:
                        viewNoteDetails();
                        break;
                    case 7:
                        searchNotes();
                        break;
                    case 8:
                        System.out.println("👋 Bye!");
                        saveNotes();
                        return;
                    default:
                        System.out.println("❗ Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Vui lòng chọn giá trị là số");
                scanner.nextLine(); // consume invalid input
            }
        }
    }

    private static void addNote() {
        System.out.print("Enter note: ");
        scanner.nextLine(); // consume the leftover newline character
        String noteContent = scanner.nextLine(); // now accept the note content correctly
        Note note = new Note(noteContent);
        notes.add(note);
        log("Added note: " + noteContent);
        saveNotes();
    }

    private static void viewNotesAsJSON() {
        if (notes.isEmpty()) {
            System.out.println("❗ No notes available.");
            return;
        }

        StringBuilder json = new StringBuilder();
        json.append("{\n  \"notes\": [\n");

        for (int i = 0; i < notes.size(); i++) {
            json.append("    \"").append(notes.get(i).getContent()).append("\"");
            if (i < notes.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("  ]\n}");
        System.out.println("\n📦 Notes in JSON format:");
        System.out.println(json);
    }

    private static void simulateMultiUserLogging() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable user1 = () -> {
            for (int i = 1; i <= 3; i++) {
                log("UserA added note " + i);
            }
        };

        Runnable user2 = () -> {
            for (int i = 1; i <= 3; i++) {
                log("UserB added note " + i);
            }
        };

        executor.submit(user1);
        executor.submit(user2);
        executor.shutdown();

        try {
            Thread.sleep(1000);
            System.out.println("\n📜 Log Output:");
            System.out.println(logBuffer.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void deleteNote() {
        viewNotes();
        int index = getIntInput("Enter note number to delete: ") - 1;

        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
            saveNotes();
            System.out.println("✅ Note deleted.");
        } else {
            System.out.println("❗ Invalid note number.");
        }
    }

    private static void editNote() {
        viewNotes();
        int index = getIntInput("Enter note number to edit: ") - 1;

        if (index >= 0 && index < notes.size()) {
            System.out.print("Enter new content: ");
            scanner.nextLine(); // consume newline
            String newContent = scanner.nextLine();
            notes.get(index).setContent(newContent);
            saveNotes();
            System.out.println("✅ Note updated.");
        } else {
            System.out.println("❗ Invalid note number.");
        }
    }

    private static void viewNoteDetails() {
        viewNotes();
        int index = getIntInput("Enter note number to view details: ") - 1;

        if (index >= 0 && index < notes.size()) {
            Note selectedNote = notes.get(index);
            System.out.println("\n📑 Note Details:");
            System.out.println("Content: " + selectedNote.getContent());
            System.out.println("Created At: " + selectedNote.getCreatedAt());
        } else {
            System.out.println("❗ Invalid note number.");
        }
    }

    private static void searchNotes() {
        System.out.println("\n🔍 Search by:");
        System.out.println("1. Content");
        System.out.println("2. Date (yyyy-MM-dd)");

        int type = getIntInput("Choose (1 or 2): ");

        if (type == 1) {
            System.out.print("Enter keyword to search: ");
            scanner.nextLine(); // consume newline
            String keyword = scanner.nextLine().toLowerCase();
            List<Note> found = new ArrayList<>();
            for (Note note : notes) {
                if (note.getContent().toLowerCase().contains(keyword)) {
                    found.add(note);
                }
            }

            if (found.isEmpty()) {
                System.out.println("🔍 No notes found with keyword: " + keyword);
            } else {
                System.out.println("\n🔍 Found " + found.size() + " note(s):");
                for (Note note : found) {
                    System.out.println("- " + note);
                }
            }

        } else if (type == 2) {
            System.out.print("Enter date (yyyy-MM-dd): ");
            scanner.nextLine(); // consume newline
            String dateInput = scanner.nextLine();
            // Handle searching by date logic
        } else {
            System.out.println("❗ Invalid choice.");
        }
    }

    private static void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("❗ No notes available.");
        } else {
            System.out.println("\n📖 Notes List:");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i).getContent());
            }
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("❗ Invalid input. Please enter a valid number.");
            scanner.nextLine(); // consume invalid input
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    private static void log(String message) {
        logBuffer.append("[LOG] ").append(message).append("\n");
    }

    private static void saveNotes() {
        try {
            // Save notes as JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("notes.json"), notes);
            System.out.println("✅ Notes saved to notes.json.");
        } catch (IOException e) {
            System.out.println("❗ Error saving notes as JSON: " + e.getMessage());
        }
    }

    private static void loadNotes() {
        try {
            // Load notes from JSON
            ObjectMapper objectMapper = new ObjectMapper();
            notes = Arrays.asList(objectMapper.readValue(new File("notes.json"), Note[].class));
            System.out.println("✅ Notes loaded from notes.json.");
        } catch (IOException e) {
            System.out.println("❗ Error loading notes from JSON: " + e.getMessage());
        }
    }
}
