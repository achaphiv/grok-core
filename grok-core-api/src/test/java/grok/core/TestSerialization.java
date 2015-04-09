package grok.core;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.geojson.Point;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

public class TestSerialization {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final Route climb = Route.create()
                                          .id(1l)
                                          .cragId(1l)
                                          .name("name").grokRating(Rating.of(1500))
                                          .image("image url")
                                          .thumbsDown(Count.of(0))
                                          .thumbsUp(Count.of(100))
                                          .grade(ClimbingGrade.yds("5.5"))
                                          .build();
  private static final Crag crag = Crag.create()
                                       .id(1l)
                                       .name("name")
                                       .country("country")
                                       .city("city")
                                       .state("state")
                                       .location(new Point(1.0, 1.0)).build();

  @Test
  public void serializesClimbToJSON() throws Exception {
    assertThat(MAPPER.writeValueAsString(climb)).isEqualTo(climbJson());
  }

  @Test
  public void deserializesClimbFromJSON() throws Exception {
    assertThat(MAPPER.readValue(climbJson(), Route.class)).isEqualTo(climb);
  }

  @Test
  public void serializesCragToJSON() throws Exception {
    assertThat(MAPPER.writeValueAsString(crag)).isEqualTo(cragJson());
  }

  @Test
  public void deserializesCragFromJSON() throws Exception {
    assertThat(MAPPER.readValue(cragJson(), Crag.class)).isEqualTo(crag);
  }

  private String climbJson() {
    return fixture("climb.json");
  }

  private String cragJson() {
    return fixture("crag.json");
  }

  private String fixture(String filename) {
    try {
      return Resources.toString(Resources.getResource(getClass(), filename), UTF_8).trim();
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
}
