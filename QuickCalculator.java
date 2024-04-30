import java.io.PrintWriter;

  /**
   * Simpler version of the InteractiveCalculator that works with commandline arguments.
   * 
   * @author Arsal Shaikh
   */
public class QuickCalculator {
  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.err.println("Please enter expression as commandline arguments");
      System.exit(0);
    } // if

    // String[] commandline = {"1/2 + 1/2", "STORE a"};
    BFCaclulator calculator = new BFCaclulator();
    Registers registers = new Registers();

    // Declaring I/O objects
    PrintWriter pen = new PrintWriter(System.out, true);

    // Parsing the input string
    String[] commandTokens;
    char regName = '?';

    // Evaluating each expression
    for (int exp = 0; exp < args.length; exp++) { 
      commandTokens = args[exp].split(" ");

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
        System.exit(1);
      } // if
      
      // Expression Case
      // Substituting Variables
      for (int i = 0; i < commandTokens.length; i++) {
        commandTokens[i] = registers.substituteVariables(commandTokens[i]);
      } // for

      // Evaluating expression
      BigFraction rollingResult = new BigFraction(commandTokens[0]);
      char currentOperator;
      BigFraction currentArg;
      for (int j = 1; j < commandTokens.length; j += 2) {
        try {
          currentOperator = commandTokens[j].charAt(0);
          currentArg = new BigFraction(commandTokens[j+1]);
          rollingResult = InteractiveCalculator.evaluate(rollingResult, currentArg, currentOperator);
        } catch (IndexOutOfBoundsException e) {
          // if expression in wrong form
          System.err.println("Please enter a valid expression.");
          System.exit(2);
        } // try catch
      } // for

      rollingResult.simplify();
      calculator.set(rollingResult);
    
      pen.printf("%s = %s\n", args[exp], rollingResult);
      pen.flush();

    } // for
  } // main(String[])
} // class QuickCalculator