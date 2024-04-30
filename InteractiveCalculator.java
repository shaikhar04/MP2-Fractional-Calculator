import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

  /**
   * An implementation of an interactable calculator that can take multiple expressions on the terminal.
   * 
   * @author Arsal Shaikh
   */
public class InteractiveCalculator {
  
  // Methods
  // Evaluating expressions in string of two arguments
  public static BigFraction evaluate(BigFraction arg1, BigFraction arg2, char operator) throws Exception {
    
    if (operator == '+') {
      return arg1.add(arg2);
    } // if

    if (operator == '-') {
      return arg1.subtract(arg2);
    } // if

    if (operator == '*') {
      return arg1.multiply(arg2);
    } // if

    if (operator == '/') {
      return arg1.divide(arg2);
    } // if

    // if not a valid operator
    throw new Exception("Please enter a valid expression.");
  } // evaluate(BigFraction, BigFraction, char)



  // Main Method
  public static void main(String[] args) throws Exception {
    BFCaclulator calculator = new BFCaclulator();
    Registers registers = new Registers();

    // Declaring I/O objects
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));
    String command;

    // Parsing the input string
    String[] commandTokens;
    char regName = '?';
    while (true) {
      // Taking user input
      pen.print("> ");
      pen.flush();
      command = eyes.readLine();
      commandTokens = command.split(" ");

      // Quit Case
      if (commandTokens[0].equals("QUIT")) {
        pen.close();
        return;
      } // if
      
      // Store Case
      if (commandTokens[0].equals("STORE")) {
        if (commandTokens.length == 2) {
          if (commandTokens[1].length() == 1) {
            regName = commandTokens[1].charAt(0);
        
            if (Character.isAlphabetic(regName)) {
              registers.store(regName, calculator.get());
              continue;
            } // if
          } // if
        } // if

        System.err.println("To store a value type: STORE <registerName>");
        pen.close();
        System.exit(0);
      } // if
      
      // Expression Case
      // Substituting Variables
      for (int i = 0; i < commandTokens.length; i++) {
        commandTokens[i] = registers.substituteVariables(commandTokens[i]);
      } // for

      BigFraction rollingResult = new BigFraction(commandTokens[0]);
      char currentOperator;
      BigFraction currentArg;
      for (int i = 1; i < commandTokens.length; i += 2) {
        try {
          currentOperator = commandTokens[i].charAt(0);
          currentArg = new BigFraction(commandTokens[i+1]);
          rollingResult = evaluate(rollingResult, currentArg, currentOperator);
        } catch (IndexOutOfBoundsException e) {
          // if expression in wrong form in fail
          System.err.println("Please enter a valid expression");
          System.exit(1);
        } // try catch
      } // for

      rollingResult.simplify();
      calculator.set(rollingResult);
      
      if (rollingResult.num.compareTo(rollingResult.denom) == 1) {
        if (!rollingResult.denom.equals(BigInteger.ONE)) {
          pen.printf("%s \t (or %s)\n", rollingResult.toString(), rollingResult.toMixedNumber());
          pen.flush();
          continue;
        } // if
      } // if
      
      // Printing result
      pen.println(rollingResult);
      pen.flush();
    } // while(true)
  } // main(String[])
} // class InteractiveCalculator
