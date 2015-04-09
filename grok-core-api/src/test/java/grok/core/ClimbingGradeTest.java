package grok.core;

import static org.assertj.core.api.Assertions.assertThat;
import grok.core.ClimbingGrade.System;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClimbingGradeTest {
  @Test
  public void somethingExists() {
    ClimbingGrade.of(System.USA, "5.1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void somethingDoesntExist() {
    ClimbingGrade.of(System.USA, "shouldNotExist");
  }

  @Test
  public void isLogicallyOrdered() {
    ClimbingGrade seven = ClimbingGrade.of(System.AUSTRALIA, "7");
    ClimbingGrade eight = ClimbingGrade.of(System.AUSTRALIA, "8");
    assertThat(seven).isLessThan(eight);
  }

  @Test
  public void canSerializeToJson() throws Exception {
    String value = "E4 6a";
    ClimbingGrade someGrade = ClimbingGrade.of(System.UNITED_KINGDOM, value);
    String actual = new ObjectMapper().writeValueAsString(someGrade);
    assertThat(actual).contains('\"' + value + '\"');
  }
}
