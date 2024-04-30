

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

} // class BFCalculator