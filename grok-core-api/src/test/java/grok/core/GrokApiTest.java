package grok.core;

import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.geojson.Point;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Client;
import feign.Feign;
import feign.Request;
import feign.Request.Options;
import feign.Response;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class GrokApiTest {
  @Test
  public void crags() throws Exception {
    Crag c = Crag.create()
                 .id(Id.of("1"))
                 .name("crag")
                 .city("City")
                 .country("Country")
                 .state("State")
                 .location(new Point(1.0, 1.0))
                 .build();
    List<Crag> expected = Arrays.asList(c);

    GrokApi toTest = feign(client(GrokApi.CRAGS_ENDPOINT, json(expected)));
    List<Crag> actual = toTest.crags();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void climbs() throws Exception {
    Route expected = Route.create()
                          .id(Id.of("1"))
                          .cragId(Id.of("1"))
                          .grokRating(Rating.of(0))
                          .image(Image.of("1", "title", "url"))
                          .name("Super Climb")
                          .grade(ClimbingGrade.yds("5.5")).thumbsDown(Count.of(0)).thumbsUp(Count.of(1))
                          .searchIndex("Super Climb 5.5")
                          .build();

    GrokApi toTest = feign(client(GrokApi.ROUTES_ENDPOINT + "/dummy", json(expected)));
    Route actual = toTest.route("dummy");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testRoutesWithCrag() throws Exception {

    Crag expectedCrag = Crag.create()
        .id(Id.of("1"))
        .name("a crag")
        .location(new Point(1.0, 1.0))
        .build();

    Route expectedRoute = Route.create()
        .id(Id.of("1"))
        .cragId(Id.of("1"))
        .grokRating(Rating.of(0))
        .image(Image.of("1", "title", "url"))
        .name("Super Climb")
        .grade(ClimbingGrade.yds("5.5")).thumbsDown(Count.of(0)).thumbsUp(Count.of(1))
        .searchIndex("Super Climb 5.5")
        .build();

    List<RouteWithCrag> expected = Lists.newArrayList(RouteWithCrag.of(expectedRoute, expectedCrag));

    Client client = client(GrokApi.ROUTES_ENDPOINT + "/withCrag/?query=&crag=1&start=0&size=50", json(expected));
    GrokApi toTest = feign(client);
    List<RouteWithCrag> actual = toTest.routesWithCrag("", Id.of("1"), 0, 50);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUpdateRoute() throws Exception {
    Route route = Route.create()
        .id(Id.of("1"))
        .cragId(Id.of("1"))
        .grokRating(Rating.of(0))
        .image(Image.of("1", "title", "url"))
        .name("Super Climb")
        .grade(ClimbingGrade.yds("5.5")).thumbsDown(Count.of(0)).thumbsUp(Count.of(1))
        .searchIndex("Super Climb 5.5")
        .build();
    Id expected = Id.of("123123");

    GrokApi toTest = feign(client(GrokApi.ROUTES_ENDPOINT + "/", json(expected)));
    Id actual = toTest.update(route);

    assertThat(actual).isEqualTo(expected);
  }

  private static GrokApi feign(Client client) {
    return Feign.builder()
                .client(client)
                .logger(new Slf4jLogger())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(GrokApi.class, "http://fake_unused_url");
  }

  private static Client client(final String requestPath, final String response) {
    return new Client() {
      @Override
      public Response execute(Request request, Options options) throws IOException {
        if (request.url().endsWith(requestPath)) {
          return Response.create(
              200,
              "reason",
              Collections.<String, Collection<String>> emptyMap(),
              response,
              StandardCharsets.UTF_8
          );
        }
        throw new IOException("Unexpected request: " + request);
      }
    };
  }

  private static String json(Object json) throws Exception {
    return new ObjectMapper().writeValueAsString(json);
  }
}
