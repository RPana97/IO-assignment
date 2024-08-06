package org.example;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Define file names
        String inputFile1 = "input1.txt";
        String inputFile2 = "input2.txt";
        String mergedFile = "merged.txt";
        String commonFile = "common.txt";

        try {
            // Read integers from the resources files
            List<Integer> list1 = readIntegersFromResource(inputFile1);
            List<Integer> list2 = readIntegersFromResource(inputFile2);

            // Merge the two lists
            List<Integer> mergedList = new ArrayList<>(list1);
            mergedList.addAll(list2);
            // Write the merged list to the merged.txt file
            writeIntegersToFile(mergedList, mergedFile);

            // Find common integers between the two lists
            List<Integer> commonList = findCommonIntegers(list1, list2);
            // Write the common integers to the common.txt file
            writeIntegersToFile(commonList, commonFile);

            System.out.println("Merging and common element identification complete.");
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error in number format: " + e.getMessage());
        }
    }

    /**
     * Reads integers from a file located in the resources folder.
     *
     * @param resource The name of the resource file.
     * @return A list of integers read from the file.
     * @throws IOException If an I/O error occurs.
     * @throws NumberFormatException If a number format error occurs.
     */
    private static List<Integer> readIntegersFromResource(String resource) throws IOException, NumberFormatException {
        List<Integer> integers = new ArrayList<>();
        // Use the class loader to get the resource as an InputStream
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Main.class.getClassLoader().getResourceAsStream(resource)))) {
            String line;
            // Read each line from the file, parse it as an integer, and add it to the list
            while ((line = br.readLine()) != null) {
                integers.add(Integer.parseInt(line.trim()));
            }
        }
        return integers;
    }

    /**
     * Writes a list of integers to a file.
     *
     * @param integers The list of integers to write.
     * @param filename The name of the output file.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeIntegersToFile(List<Integer> integers, String filename) throws IOException {
        // Create a BufferedWriter to write to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // Write each integer to the file, one per line
            for (Integer integer : integers) {
                bw.write(integer.toString());
                bw.newLine();
            }
        }
    }

    /**
     * Finds common integers between two lists.
     *  list1 The first list of integers.
     *  list2 The second list of integers.
     *  returns A list of integers that are present in both lists.
     */
    private static List<Integer> findCommonIntegers(List<Integer> list1, List<Integer> list2) {
        // Convert the first list to a set for fast lookups
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> commonSet = new HashSet<>();
        // Check each integer in the second list to see if it's in the first set
        for (Integer integer : list2) {
            if (set1.contains(integer)) {
                commonSet.add(integer);
            }
        }
        return new ArrayList<>(commonSet);
    }
}
