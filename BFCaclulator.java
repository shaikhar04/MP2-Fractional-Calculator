

public class BFCaclulator {
  /**
   * A simple implementation of calculator functions.
   * 
   * @author Arsal Shaikh
   */
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  // Stores the last value calculated
  BigFraction lastValue;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+
  public BFCaclulator() throws Exception {
    lastValue = new BigFraction(0, 1);
  } // BFCalculator()
  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  // Returns last value in calculator
  public BigFraction get() {
    return lastValue;
  } // get()

  // Sets last value to val
  public void set(BigFraction val) {
    lastValue = val;
  } // set(BigFraction)

  public void simplify() throws Exception {
    lastValue = lastValue.simplify();
  }

  // Adds val to the last computed value
  public void add(BigFraction val) throws Exception {
    lastValue = lastValue.add(val);
  } // add(BigFraction)

  // Subtracts val from the last computed value
  public void subtract(BigFraction val) throws Exception {
    lastValue = lastValue.subtract(val);
  } // subtract(BigFraction)

  // Multiplies last computed value by val
  public void multiply(BigFraction val) throws Exception {
    lastValue = lastValue.multiply(val);
  } // multiply(BigFraction)

  // Divides last computed value by val
  public void divide(BigFraction val) throws Exception {
    lastValue = lastValue.divide(val);
  } // divide(BigFraction)

  // Resets the last computed value to zero
  public void reset() throws Exception {
    lastValue = new BigFraction(0, 1);
  } // reset()

  // Evaluating expressions in string of two arguments
  public void evaluate(BigFraction arg, char operator) throws Exception {

    if (operator == '+') {
      add(arg);
    } else if (operator == '-') {
      subtract(arg);
    } else if (operator == '*') {
      multiply(arg);
    } else if (operator == '/') {
      divide(arg);
    } else {
      // if not a valid operator
      throw new Exception("Please enter a valid expression.");
    } // if else
  } // evaluate(BigFraction, BigFraction, char)
} // class BFCalculator
