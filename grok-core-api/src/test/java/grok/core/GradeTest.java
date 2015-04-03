package grok.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GradeTest {
  @Test
  public void serializeAsJsonString() throws Exception {
    Grade someGrade = new Grade() {
      @Override
      public String getValue() {
        return "FOO";
      }
    };
    String actual = new ObjectMapper().writeValueAsString(someGrade);
    assertThat(actual).isEqualTo("\"FOO\"");
  }

  @Test
  public void canDeserializeAsFrenchGrade() {
    Grade.from(new FrenchGrade("7a").getValue());
  }
}
