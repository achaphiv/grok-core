package grok.core;

import java.util.List;

import feign.Param;
import feign.RequestLine;
import grok.core.request.SearchRequest;

public interface GrokApi {
  String CRAGS_ENDPOINT = "/crag";
  String ROUTES_ENDPOINT = "/route";
  int DEFAULT_PAGE_SIZE = 10;

  @RequestLine("GET " + CRAGS_ENDPOINT)
  List<Crag> crags();

  @RequestLine("GET " + CRAGS_ENDPOINT + "/{id}")
  Crag crag(@Param("id") String id);

  @RequestLine("POST " + CRAGS_ENDPOINT)
  void insertCrag(Crag c);

  @RequestLine("GET " + CRAGS_ENDPOINT + "/{id}/routes")
  List<Route> getRoutesForCrag(@Param("id") String id);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/{id}")
  Route route(@Param("id") String id);

  @RequestLine("POST " + ROUTES_ENDPOINT)
  void insertRoute(Route r);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/search?query={query}&start={start}&size={size}")
  List<Route> searchRoutes(@Param("query") String query, @Param("start") int start, @Param("size") int size);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/search?query={query}")
  List<Route> searchRoutes(@Param("query") String query);

  @RequestLine("POST " + ROUTES_ENDPOINT + "/search?query={query}&start={start}&size={size}")
  List<Route> searchRoutes(@Param("search") SearchRequest request, @Param("start") int start, @Param("size") int size);
}
