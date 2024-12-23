import java.io.*;
import java.util.Scanner;

public class CodeProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("Welcome to the Cipher program.");
            System.out.println("Type 1 for Substitution Cipher.");
            System.out.println("Type 2 for Shuffle Cipher.");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Cipher cipher;
            if (choice == 1) {
                System.out.print("What is the key (shift amount) for your code? ");
                int shift = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                cipher = new SubstitutionCipher(shift);
            } else {
                System.out.print("What is the key (shuffle amount) for your code? ");
                int shuffleCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                cipher = new ShuffleCipher(shuffleCount);
            }

            System.out.print("Enter input file name: ");
            String inputFileName = scanner.nextLine();
            System.out.print("Enter output file name: ");
            String outputFileName = scanner.nextLine();

            System.out.print("Encode (E) or Decode (D): ");
            char operation = scanner.nextLine().toUpperCase().charAt(0);

            try {
                // Read input from file
                String inputText = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(inputFileName)));

                // Perform encoding or decoding
                String result = (operation == 'E')
                        ? ((MessageEncoder) cipher).encode(inputText)
                        : ((MessageDecoder) cipher).decode(inputText);

                // Write result to output file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                    writer.write(result);
                }

                System.out.println((operation == 'E' ? "Encoded" : "Decoded") + " text is saved in " + outputFileName);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Ask if the user wants to continue
            System.out.print("Do you want to do another message (Y/N)? ");
            char continueChoice = scanner.nextLine().toUpperCase().charAt(0);
            if (continueChoice != 'Y') {
                break;
            }
        }

        scanner.close();
    }
}
