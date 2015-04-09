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
  private static final Crag crag = Crag.create()
                                       .id(1l)
                                       .name("name")
                                       .country("country")
                                       .city("city")
                                       .state("state")
                                       .location(new Point(1.0, 1.0)).build();

  @Test
  public void serializesCragToJSON() throws Exception {
    assertThat(MAPPER.writeValueAsString(crag)).isEqualTo(cragJson());
  }

  @Test
  public void deserializesCragFromJSON() throws Exception {
    assertThat(MAPPER.readValue(cragJson(), Crag.class)).isEqualTo(crag);
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
