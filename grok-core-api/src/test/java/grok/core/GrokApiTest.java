package grok.core;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.geojson.Point;
import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import feign.Feign;
import feign.jackson.JacksonDecoder;

public class GrokApiTest {
  @Rule
  public final WireMockRule server = new WireMockRule(0);

  private GrokApi client() {
    return Feign.builder()
                .decoder(new JacksonDecoder())
                .target(GrokApi.class, "http://localhost:" + server.port());
  }

  @Test
  public void crags() throws Exception {
    Crag c = Crag.create()
                 .id(1l)
                 .name("crag")
                 .city("City")
                 .country("Country")
                 .state("State")
                 .regionId(1l)
                 .location(new Point(1.0, 1.0))
                 .build();
    List<Crag> expected = Arrays.asList(c);

    server.stubFor(get(urlEqualTo(GrokApi.CRAGS_ENDPOINT)).willReturn(aResponse().withBody(json(expected))));
    List<Crag> actual = client().crags();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void climbs() throws Exception {
    Route expected = Route.create()
                          .id(1l)
                          .cragId(1l).grokRating(Rating.of(0))
                          .image("image url")
                          .name("Super Climb")
                          .grade(Grade.from("5.5")).thumbsDown(Count.of(0)).thumbsUp(Count.of(1))
                          .searchIndex("Super Climb 5.5")
                          .build();

    server.stubFor(get(urlEqualTo(GrokApi.ROUTES_ENDPOINT + "/dummy")).willReturn(aResponse().withBody(json(expected))));
    Route actual = client().route("dummy");

    assertThat(actual).isEqualTo(expected);
  }

  private static String json(Object json) throws Exception {
    return new ObjectMapper().writeValueAsString(json);
  }
}
