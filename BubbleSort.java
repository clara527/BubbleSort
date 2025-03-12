// Clara Yalamanchili CSI 2300
// Assignment 7 Bubble Sort

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    public static int[] createRandomArray(int length) {
        Random rand = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = rand.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int number : array) {
                writer.write(number + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public static int[] readArrayFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int[] array = new int[0];
            while ((line = reader.readLine()) != null) {
                int[] temp = new int[array.length + 1];
                System.arraycopy(array, 0, temp, 0, array.length);
                temp[array.length] = Integer.parseInt(line);
                array = temp;
            }
            return array;
        } catch (IOException e) {
            System.out.println("Error reading from file.");
            return new int[0];
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Generate a random array and store it in a file.");
        System.out.println("2. Read an existing file, sort it, and store the sorted array in another file.");
        int option = scanner.nextInt();

        if (option == 1) {
            System.out.print("Enter the length of the array: ");
            int arrayLength = scanner.nextInt();
            System.out.print("Enter the filename to save the array: ");
            scanner.nextLine(); // consume the newline character
            String filename = scanner.nextLine();

            // Generate random array and print it
            int[] numbers = createRandomArray(arrayLength);
            System.out.println("Original array:");
            for (int number : numbers) {
                System.out.print(number + " ");
            }
            System.out.println();
            writeArrayToFile(numbers, filename);
            System.out.println("Array has been saved to " + filename);

        } else if (option == 2) {
            System.out.print("Enter the filename to read the array from: ");
            scanner.nextLine(); // consume the newline character
            String filename = scanner.nextLine();
            System.out.print("Enter the filename to save the sorted array: ");
            String outputFilename = scanner.nextLine();

            // Read array from file
            int[] numbersFromFile = readArrayFromFile(filename);
            System.out.println("Original array from file:");
            for (int number : numbersFromFile) {
                System.out.print(number + " ");
            }
            System.out.println();

            // Sort the array
            bubbleSort(numbersFromFile);
            System.out.println("Sorted array:");
            for (int number : numbersFromFile) {
                System.out.print(number + " ");
            }
            System.out.println();
            writeArrayToFile(numbersFromFile, outputFilename);
            System.out.println("Sorted array has been saved to " + outputFilename);

        } else {
            System.out.println("Invalid option.");
        }
    }
}
