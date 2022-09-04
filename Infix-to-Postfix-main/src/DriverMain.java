import java.util.Scanner;
import java.util.Stack;

public class DriverMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Infix Expression");
        String input = sc.next();
        System.out.println(input);
        String postfix = infixToPostFix(input);
        System.out.println("PostFix Expression:"+postfix);
    }
public static String infixToPostFix(String inp){
        String postfix = "";
        int precedenceCurr, precedenceTop;
        char [] charArr;
        Stack<Character> stack = new Stack<>();
        charArr=inp.toCharArray();
    for (char c : charArr) {
        if (c != ')') {
            precedenceCurr = precedence(c);
            if (precedenceCurr == 0) {
                postfix = postfix + c;
                continue;
            }
            if (stack.isEmpty() || stack.peek() == '(') {
                stack.push(c);
            } else {
                precedenceTop = precedence(stack.peek());
                while (precedenceTop >= precedenceCurr && stack.peek() != '(') {
                    postfix = postfix + stack.pop();
                    if (!stack.isEmpty()) {
                        precedenceTop = precedence(stack.peek());
                    } else {
                        break;
                    }

                }
                stack.push(c);

            }
        } else {
            while (stack.peek() != '(') {
                if (!stack.isEmpty()) {
                    postfix = postfix + stack.pop();
                } else {
                    System.out.println("Invalid Input, braces donot match");
                    return ("INVALID INPUT");
                }

            }
            stack.pop();
        }
    }
        while(!stack.isEmpty()){
            postfix = postfix + stack.pop();
        }
        return postfix;
     }
    public static int precedence(char ch){
        if(ch == '('){
            return 4;
        }
        else if(ch == '^'){
            return 3;
        }
        else if(ch == '/' || ch == '*'){
            return 2;
        }
        else if (ch == '-' || ch == '+') {
            return 1;
        }
        else{
            return 0;
        }

    }


}
//abcd^e-fgh*+^*+i-