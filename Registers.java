  /**
   * Registers class that can store BigFractions in 26 registers named according to the alphabet.
   * 
   * @author Arsal Shaikh
   */
public class Registers {
  String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  BigFraction[] registers = new BigFraction[26];

  // Constructor
  // Initializes all 
  public Registers() throws Exception {
    for (int i = 0; i < registers.length; i++) {
      registers[i] = new BigFraction(0, 1);
    }
  }

  // Identify index of by register name
  private int registerIndex(char registerName) {
    return (int) Character.toUpperCase(registerName) - 65;
  } // registerIndex(char)

  // Stores the given value into the register
  public void store(char register, BigFraction val) throws Exception {
    val = val.simplify();
    registers[registerIndex(register)] = val;
  } // store(char, BigFraction)

  // Retrieves the value from the given register
  public BigFraction get(char register) {
    return registers[registerIndex(register)];
  } // get(char)


  // Substitute variables in string with numbers
  public String substituteVariables(String expression) {
    String[] expressionTokens = expression.split(" ");
    String outString = "";
    char currentChar;
    for (int i = 0; i < expressionTokens.length; i++) {
      currentChar = expressionTokens[i].charAt(i);
      
      // Register name can only be single character [a-z]
      if ((Character.isAlphabetic(currentChar)) && (expressionTokens[i].length() == 1)) {
        outString += this.get(currentChar).toString();
        continue;
      } // if

      outString += expressionTokens[i];
    } // for
    return outString;
  } // substituteVariables(String)

} // class Registers
