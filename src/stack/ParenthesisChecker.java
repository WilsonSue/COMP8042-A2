package src.stack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParenthesisChecker {

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    public static void checkFile(String filename) {
        Stack<Character> stack = new ArrayListStack<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int lineNumber = 1;
            String line;

            while ((line = reader.readLine()) != null) { // Until end of file
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);

                    if (c == '(' || c == '{' || c == '[') { // push opening brackets onto the stack
                        stack.push(c);
                    }
                    else if (c == ')' || c == '}' || c == ']') { // process closing brackets
                        if (stack.empty()) {  // if stack is empty = a closing bracket with no opener
                            System.out.println("Extra closing detected '" + c + "' on line " + lineNumber);
                            return;
                        }

                        char open = stack.pop();
                        if (!isMatchingPair(open, c)) {
                            System.out.println("Found '" + c + "' on line " + lineNumber + " but expected match for '" + open + "'");
                            return;
                        }
                    }
                }
                lineNumber++;
            }

            if (!stack.empty()) { // If loop finishes but stack isn't empty = an opening bracket was never closed
                System.out.println("File ended but there is an unclosed '" + stack.peek() + "' left in the stack.");
            } else {
                System.out.println("All parentheses are matched succesfully");
            }

        } catch (IOException e) {
            System.out.println("Error in reading file: " + filename );
        }
    }

    // Run test
    public static void main(String[] args) {
        // Declare Test files
        String[] files = {
                "src/stack/StackParenthesesTask1.txt",
                "src/stack/StackParenthesesTask2.txt",
                "src/stack/StackParenthesesTask3.txt",
                "src/stack/StackParenthesesTask4.txt"
        };

        for (String file : files) {
            System.out.println("Checking " + file);
            checkFile(file);
            System.out.println("---------------------------------");
        }
    }
}