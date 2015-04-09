package grok.core;

import static org.assertj.core.api.Assertions.assertThat;
import grok.core.ClimbingGrade.System;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClimbingGradeTest {
  @Test
  public void somethingExists() {
    ClimbingGrade.of("5.1", System.YDS);
  }

  @Test(expected = IllegalArgumentException.class)
  public void somethingDoesntExist() {
    ClimbingGrade.of("shouldNotExist", System.YDS);
  }

  @Test
  public void isLogicallyOrdered() {
    ClimbingGrade seven = ClimbingGrade.of("7", System.EWBANK);
    ClimbingGrade eight = ClimbingGrade.of("8", System.EWBANK);
    assertThat(seven).isLessThan(eight);
  }

  @Test
  public void canSerializeToJson() throws Exception {
    String value = "E4 6a";
    ClimbingGrade someGrade = ClimbingGrade.of(value, System.BRITISH);
    String actual = new ObjectMapper().writeValueAsString(someGrade);
    assertThat(actual).contains('\"' + value + '\"');
  }
}
