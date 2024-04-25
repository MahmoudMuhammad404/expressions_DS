import java.util.Stack;

public class Exp_Of_Stacks {
    int size;
    int top;
    int[] arr;

    public Exp_Of_Stacks() {
    }

    public Exp_Of_Stacks(int size) {
        this.size = size;
        top = -1;
        arr = new int[size];
    }

    public void push(int newV) {
        if (isFull()) {
            System.out.println("Stack is full,we cant push any element");
        } else
            arr[++top] = newV;
    }

    public int pop() {
        if (!isEmpty()) {
            return arr[top--];
        } else
            return -1;
    }

    public int peek() {
        return arr[top];
    }

    public int top() {
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return (top == size - 1);
    }

    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }

    }

    public static boolean isOperator(char x) {
        switch (x) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        else if (op1 == '*' || op1 == '/' && op2 == '+' || op2 == '-')
            return false;
        return true;
    }

    public int ApplyOP(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    System.out.println("Division by 0 are not allowed");
                else
                    return a / b;
        }
        return 0;
    }

    public String Con_infix_TO_postfix(String infix_exp) {
        Stack<Character> stack = new Stack<>();
        StringBuffer postfix = new StringBuffer(infix_exp.length());
        int l = infix_exp.length();
        for (int i = 0; i < l; i++) {
            char c = infix_exp.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return null;
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    public String Con_prefix_TO_infix(String prefix_exp) {
        Stack<String> stack = new Stack<>();
        int l = prefix_exp.length();
        for (int i = l - 1; i >= 0; i--) {
            char c = prefix_exp.charAt(i);
            if (isOperator(c)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String result = "(" + op1 + c + op2 + ")";
                stack.push(result);
            } else
                stack.push(c + " ");
        }
        return stack.pop();
    }

    public String Con_prefix_TO_postfix(String prefix) {
        Stack<String> stack = new Stack<>();
        int l = prefix.length();
        for (int i = l - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (isOperator(c)) {
                String val1 = stack.pop();
                String val2 = stack.pop();
                String result = "(" + val1 + val2 + c + ")";
                stack.push(result);
            } else
                stack.push(c + " ");
        }
        return stack.pop();
    }

    public String Con_postfix_TO_prefix(String postfix) {
        Stack<String> stack = new Stack<>();
        int l = postfix.length();
        for (int i = 0; i < l; i++) {
            char c = postfix.charAt(i);
            if (isOperator(c)) {
                String v1 = stack.pop();
                String v2 = stack.pop();
                String re = "(" + c + v2 + v1 + ")";
                stack.push(re);
            } else
                stack.push(c + "");
        }
        return stack.pop();
    }

    public String Con_postfix_TO_infix(String postfix) {
        Stack<String> stack = new Stack<>();
        int l = postfix.length();
        for (int i = 0; i < l; i++) {
            char c = postfix.charAt(i);
            if (isOperator(c)) {
                String v1 = stack.pop();
                String v2 = stack.pop();
                String re = "(" + v1 + c + v2 + ")";
                stack.push(re);
            } else
                stack.push(c + "");
        }
        return stack.pop();
    }

    public boolean isBalanced(String exp) {
        Stack<Character> stack = new Stack<>();
        int l = exp.length();
        for (int i = 0; i < l; i++) {
            char c = exp.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char check;
                switch (c) {
                    case ')':
                        check = stack.pop();
                        if (check == '[' || check == '{')
                            return false;
                        break;
                    case ']':
                        check = stack.pop();
                        if (check == '(' || check == '{')
                            return false;
                        break;
                    case '}':
                        check = stack.pop();
                        if (check == '[' || check == '(')
                            return false;
                        break;
                }
            }
        }
        if (!stack.isEmpty())
            return false;
        else
            return true;
    }

    public int Evaluate_infix_exp(String exp) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] tokens = exp.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer s = new StringBuffer();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                    s.append(tokens[i++]);
                }
                values.push(Integer.parseInt(s.toString()));

                i--;

            } else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(ApplyOP(operators.pop(), values.pop(), values.pop()));
                    operators.pop(); // to remove the '('cd
                }
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                    values.push(ApplyOP(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);
            }
        }
        while (!operators.isEmpty()) {
            values.push(ApplyOP(operators.pop(), values.pop(), values.pop()));
        }
        return values.pop();
    }

    public int evaluate_postfix_exp(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        if (val1 == 0) {
                            System.out.println("we can not division by zero");
                        }
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    public int evaluate_prefix_exp(String prefix) {
        Stack<Integer> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(val1 + val2);
                        break;
                    case '-':
                        stack.push(val1 - val2);
                        break;
                    case '*':
                        stack.push(val1 * val2);
                        break;
                    case '/':
                        if (val2 == 0) {
                            System.out.println("we can not division by zero");
                        }
                        stack.push(val1 / val2);
                        break;
                }
            }
        }
        return stack.pop();
    }

}
