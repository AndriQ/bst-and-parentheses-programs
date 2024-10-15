import java.util.Stack;
import java.util.Scanner;

public class ValidParentheses {
   
    public static boolean balanced(String string) {
        Stack<Character> stack = new Stack<>();
        for (char character : string.toCharArray()) {
            if (character == '(' || character == '{' || character == '[') {
                stack.push(character);
            } else if (character == ')' || character == '}' || character == ']') {
                if (stack.isEmpty()) {
                    return false; 
                }
                char topParenthesis = stack.pop();
                if ((character == ')' && topParenthesis != '(') || (character == '}' && topParenthesis != '{') || (character == ']' && topParenthesis != '[')) {
                    return false; 
                }
            }
        }
        return stack.isEmpty(); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input a string of parentheses:");
        String userInput = scanner.nextLine();
        scanner.close();

        if (balanced(userInput)) {
            System.out.println("The expression is valid");
        } else {
            System.out.println("The expression is not valid");
        }
    }
}
