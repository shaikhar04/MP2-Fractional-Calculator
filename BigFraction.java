import java.math.BigInteger;

/**
 * A simple implementation of Fractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Arsal Shaikh
 */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+
  /*
   * (1) Denominators are always positive. Therefore, negative fractions are represented with a
   * negative numerator. Similarly, if a fraction has a negative numerator, it is negative.
   * 
   * (2) Fractions are not necessarily stored in simplified form. To obtain a fraction in simplified
   * form, one must call the `simplify` method.
   */

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom of type BigInteger.
   */
  public BigFraction(BigInteger num, BigInteger denom) throws Exception {
    if (denom.intValue() == 0) {
      throw new ArithmeticException("Zero denominators not permitted");
    }
    this.num = num;
    this.denom = denom;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom of type int.
   */
  public BigFraction(int num, int denom) throws Exception {
    if (denom == 0) {
      throw new ArithmeticException("Zero denominators not permitted");
    }
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  } // BigFraction(int, int)

  /**
   * Build a new fraction with numerator num and denominator denom of type BigFraction.
   */
  public BigFraction(BigFraction num, BigFraction denom) throws Exception {
    if (denom.numerator() == BigInteger.valueOf(0)) {
      throw new ArithmeticException("Zero denominators not permitted");
    }

    BigFraction result = num.divide(denom);

    this.num = result.numerator();
    this.denom = result.denominator();
  } // BigFraction(BigFraction, BigFraction)

  /**
   * Build a new fraction by parsing a string.
   */
  public BigFraction(String str) {
    String[] arr = str.split("/");
    this.num = new BigInteger(arr[0]);

    if (arr.length >= 2) {
      this.denom = new BigInteger(arr[1]);
    } else {
      this.denom = BigInteger.ONE;
    }
  } // BigFraction(String)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add the fraction `addMe` to this fraction.
   * 
   * @throws Exception
   */
  public BigFraction add(BigFraction addMe) throws Exception {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultDenominator = this.denom.multiply(addMe.denom);
    resultNumerator = (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    BigFraction ret = new BigFraction(resultNumerator, resultDenominator);
    ret = ret.simplify();

    return ret;
  } // add(BigFraction)

  // Subtracts passed parameter from object
  public BigFraction subtract(BigFraction subtractMe) throws Exception {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultDenominator = this.denom.multiply(subtractMe.denom);

    resultNumerator =
        (this.num.multiply(subtractMe.denom)).subtract(subtractMe.num.multiply(this.denom));

    BigFraction ret = new BigFraction(resultNumerator, resultDenominator);
    ret = ret.simplify();

    return ret;
  } // add(BigFraction)

  // Returns the reciprocal of the BigFraction
  public BigFraction inverse() throws Exception {
    BigFraction ret = new BigFraction(this.denominator(), this.numerator());
    ret = ret.simplify();
    return ret;
  } // inverse()


  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    if (this.denom.equals(BigInteger.ONE)) {
      return this.num.toString();
    }

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

  // Simplifies BigFraction into reduced form.
  public BigFraction simplify() throws Exception {
    BigInteger gcd = this.denominator().gcd(this.numerator());
    BigInteger simple_denominator = this.denom.divide(gcd);
    BigInteger simple_numerator = this.num.divide(gcd);
    BigFraction simplified = new BigFraction(simple_numerator, simple_denominator);

    return simplified;
  } // simplify()

  public BigFraction multiply(BigFraction f) throws Exception {
    BigInteger prod_numerator = this.numerator().multiply(f.numerator());
    BigInteger prod_denominator = this.denominator().multiply(f.denominator());
    BigFraction prod = new BigFraction(prod_numerator, prod_denominator);
    prod = prod.simplify();
    return prod;
  } // multiply(BigFraction f)

  public BigFraction divide(BigFraction f) throws Exception {
    BigInteger quotient_numerator = this.numerator().multiply(f.denominator());
    BigInteger quotient_denominator = this.denominator().multiply(f.numerator());
    BigFraction quotient = new BigFraction(quotient_numerator, quotient_denominator);
    quotient = quotient.simplify();
    return quotient;
  } // divide(BigFraction f)

  // Returns the fractional component of mixed number format
  public BigFraction fractional() throws Exception {
    BigInteger numerator = this.numerator();
    BigInteger denominator = this.denominator();

    numerator = numerator.mod(denominator);
    BigFraction fractional_value = new BigFraction(numerator, denominator);
    fractional_value = fractional_value.simplify();

    return fractional_value;
  } // fractional()

  // Returns BigFraction as a String in mixed number form
  public String toMixedNumber() throws Exception {
    BigInteger wholePart = this.num.divide(denom);
    BigFraction fractionalPart = this.fractional();

    return String.valueOf(wholePart) + " " + fractionalPart.toString();
  } // toMixedNumber()

} // class BigFraction
