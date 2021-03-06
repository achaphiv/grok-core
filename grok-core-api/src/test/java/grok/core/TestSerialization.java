package grok.core;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.geojson.Point;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

public class TestSerialization {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final Crag crag = Crag.create()
      .id(Id.of("1"))
      .name("name")
      .country("country")
      .city("city")
      .state("state")
      .image(Image.of("1", "title", "url"))
      .location(new Point(1.0, 1.0))
      .build();

  @Test
  @SuppressWarnings("unchecked")
  public void serializesCragToJSON() throws Exception {
    assertThat(MAPPER.convertValue(crag, Map.class))
        .isEqualTo(MAPPER.readValue(cragJson(), Map.class));
  }

  @Test
  public void deserializesCragFromJSON() throws Exception {
    assertThat(MAPPER.readValue(cragJson(), Crag.class)).isEqualTo(crag);
  }

  private String cragJson() throws Exception {
    return fixture("crag.json");
  }

  private String fixture(String file) throws Exception {
    return Resources.toString(Resources.getResource(getClass(), file), UTF_8);
  }
}
