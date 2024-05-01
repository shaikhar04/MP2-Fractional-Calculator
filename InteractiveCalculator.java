import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * An implementation of an interactable calculator that can take multiple expressions on the
 * terminal.
 * 
 * @author Arsal Shaikh
 */
public class InteractiveCalculator {

  // Methods

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
        try {
          store(calculator, registers, commandTokens);
          continue;
        } catch (Exception e) {
          System.err.println("To store a value type: STORE <registerName>");
          e.printStackTrace();
          pen.close();
          System.exit(0);
        } // try catch

      } // if
      
      // Expression Case
      // Substituting Variables
      for (int i = 0; i < commandTokens.length; i++) {
        commandTokens[i] = registers.substituteVariables(commandTokens[i]);
      } // for
      
        try {
          evaluate_expression(calculator, commandTokens);
        } catch (Exception e) {
          // if expression in wrong form in fail
          System.err.println("Please enter a valid expression");
          System.exit(1);
        } // try catch

      calculator.simplify();
      BigFraction result = calculator.get();
      
      if (result.num.compareTo(result.denom) == 1) {
        if (!result.denom.equals(BigInteger.ONE)) {
          pen.printf("%s \t (or %s)\n", result.toString(), result.toMixedNumber());
          pen.flush();
          continue;
        } // if
      } // if
      
      // Printing result
      pen.println(result);
      pen.flush();
    } // while(true)
  } // main(String[])

  public static void store(BFCaclulator calculator, Registers registers, String[] commandTokens) throws Exception {
    if (commandTokens.length != 2) {
      throw new Exception("Invalid arguments.");
    } // if

    if (commandTokens[1].length() != 1 && !commandTokens[1].equals("STORE")) {
      throw new Exception("Invalid commandword.");
    } // if

    char regName = commandTokens[1].charAt(0);
    
    if (!Character.isAlphabetic(regName)) {
      throw new Exception("Invalid register name");
    } // if
  
    registers.store(regName, calculator.get());
  } // store()

  public static void evaluate_expression(BFCaclulator calculator, String[] commandTokens) throws Exception {
    calculator.set(new BigFraction(commandTokens[0]));
      char currentOperator;
      BigFraction currentArg;
      for (int i = 1; i < commandTokens.length; i += 2) {
          currentOperator = commandTokens[i].charAt(0);
          currentArg = new BigFraction(commandTokens[i+1]);
          calculator.evaluate(currentArg, currentOperator);
      } // for
  } // evaluate_expression(BFCalculator, String[])
} // class InteractiveCalculator
