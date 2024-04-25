import java.util.Scanner;

public class Abdelmawgoud {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int num;
    String exp = "";
    Exp_Of_Stacks a = new Exp_Of_Stacks();
    String c;
    do {
      System.out.println("Enter your choise");
      System.out.println("1-if you want to convert infix expression to postfix expression");
      System.out.println("2-if you want to convert prefix expression to infix expression");
      System.out.println("3-if you want to convert prefix expression to postfix expression");
      System.out.println("4-if you want to convert postfix expression to prefix expression");
      System.out.println("5-if you want to convert postfix expression to infix expression");
      System.out.println("6-if you want to check Balanced");
      System.out.println("7-if you want to evaluate infix expression");
      System.out.println("8-if you want to evaluate postfix expression");
      System.out.println("9-if you want to evaluate prefix expression");

      num = in.nextInt();
      switch (num) {
        case 1:
          System.out.println("Enter the infix expression");
          exp = in.next();
          System.out.println("The postfix expression is = " + a.Con_infix_TO_postfix(exp));
          break;
        case 2:
          System.out.println("Enter the prefix expression");
          exp = in.next();
          System.out.println("The infix expression is = " + a.Con_prefix_TO_infix(exp));
          break;
        case 3:
          System.out.println("Enter the prefix expression");
          exp = in.next();
          System.out.println("The postfix expression is = " + a.Con_prefix_TO_postfix(exp));
          break;
        case 4:
          System.out.println("Enter the postfix expression");
          exp = in.next();
          System.out.println("The prefix expression is = " + a.Con_postfix_TO_prefix(exp));
          break;
        case 5:
          System.out.println("Enter the postfix expression");
          exp = in.next();
          System.out.println("The infix expression is = " + a.Con_postfix_TO_infix(exp));
          break;
        case 6:
          System.out.println("Enter the expression");
          exp = in.next();
          System.out.println("The  expression is = " + a.isBalanced(exp));
          break;
        case 7:
          System.out.println("Enter the infix expression");
          exp = in.next();
          System.out.println("the infix expression is equal = " + a.Evaluate_infix_exp(exp));
          break;
        case 8:
          System.out.println("Enter the postfix expression");
          exp = in.next();
          System.out.println("the postfix expression is equal = " + a.evaluate_postfix_exp(exp));
          break;
        case 9:
          System.out.println("Enter the prefix expression");
          exp = in.next();
          System.out.println("the prefix expression is equal = " + a.evaluate_prefix_exp(exp));
          break;

        default:
          System.out.println("invalid failed");

      }
      System.out.println("Do you want to start again? : (yes OR no )");
      c = in.next();

    } while (c.equals("yes"));
    System.out.println("Good Bye Baby");

  }
}
