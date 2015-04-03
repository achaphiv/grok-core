package grok.core;

/**
 * E.G. 7a+
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Grade_%28climbing%29#French_numerical_grades">French grade</a>
 */
public final class FrenchGrade implements Grade {
  public static boolean isValid(String value) {
    return value.matches("[1-9][abcABC]\\+?");
  }

  private final String value;

  public FrenchGrade(String value) {
    if (!isValid(value)) {
      throw new IllegalArgumentException("Invalid grade");
    }
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getValue();
  }
}
