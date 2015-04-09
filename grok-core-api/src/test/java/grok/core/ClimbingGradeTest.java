package grok.core;

import static org.assertj.core.api.Assertions.assertThat;
import grok.core.ClimbingGrade.Country;

import org.junit.Test;

public class ClimbingGradeTest {
  @Test
  public void somethingExists() {
    ClimbingGrade.of(Country.USA, "5.1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void somethingDoesntExist() {
    ClimbingGrade.of(Country.USA, "shouldNotExist");
  }

  @Test
  public void isLogicallyOrdered() {
    ClimbingGrade seven = ClimbingGrade.of(Country.AUSTRALIA, "7");
    ClimbingGrade eight = ClimbingGrade.of(Country.AUSTRALIA, "8");
    assertThat(seven).isLessThan(eight);
  }
}
