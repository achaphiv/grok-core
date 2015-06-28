package grok.core;

import java.util.List;

import feign.Param;
import feign.RequestLine;

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

  @RequestLine("GET " + ROUTES_ENDPOINT + "/{id}/withCrag")
  RouteWithCrag routeWithCrag(@Param("id") long id);

  @RequestLine("GET" + ROUTES_ENDPOINT + "/withCrag/?query={query}&lat={lat}&lng={lng}&start={start}&size={size}")
  List<RouteWithCrag> routesWithCrag(
      @Param("query") String query,
      @Param("lat") String lat,
      @Param("lng") String lng,
      @Param("start") int start,
      @Param("size") int pageSize);

  @RequestLine("GET" + ROUTES_ENDPOINT + "/withCrag/?query={query}&crag={crag}&start={start}&size={size}")
  List<RouteWithCrag> routesWithCrag(
      @Param("query") String query,
      @Param("crag") Long id,
      @Param("start") int start,
      @Param("size") int pageSize);

  @RequestLine("POST " + ROUTES_ENDPOINT)
  void insertRoute(Route r);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/?query={query}&lat={lat}&lng={lng}&start={start}&size={size}")
  List<Route> searchRoutes(@Param("query") String query,
                           @Param("lat") Double lat,
                           @Param("lng") Double lng,
                           @Param("start") int start,
                           @Param("size") int size);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/?query={query}&start={start}&size={size}")
  List<Route> searchRoutes(@Param("query") String query,
                           @Param("start") int start,
                           @Param("size") int size);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/?query={query}")
  List<Route> searchRoutes(@Param("query") String query);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/?query={query}&crag={crag}&lat={lat}&lng={lng}&start={start}&size={size}")
  List<Route> searchRoutes(@Param("query") String query,
                           @Param("crag") String crag,
                           @Param("lat") Double lat,
                           @Param("lng") Double lng,
                           @Param("start") int start,
                           @Param("size") int size);

  @RequestLine("GET " + ROUTES_ENDPOINT + "/?query={query}&crag={crag}")
  List<Route> searchRoutes(@Param("query") String query,
                           @Param("crag") String crag);

  @RequestLine("GET " + CRAGS_ENDPOINT + "/?query={query}&lat={lat}&lng={lng}&start={start}&size={size}")
  List<Crag> searchCrags(@Param("query") String query,
                         @Param("lng") Double longitude,
                         @Param("lat") Double latitude,
                         @Param("start") int start,
                         @Param("size") int size);

  @RequestLine("GET " + CRAGS_ENDPOINT + "/?query={query}&lat={lat}&lng={lng}")
  List<Crag> searchCrags(@Param("query") String query,
                         @Param("lng") Double longitude,
                         @Param("lat") Double latitude);

  @RequestLine("GET " + CRAGS_ENDPOINT + "/?lat={lat}&lng={lng}")
  List<Crag> searchCrags(@Param("lng") Double longitude,
                         @Param("lat") Double latitude);

  @RequestLine("GET " + CRAGS_ENDPOINT + "/?lat={lat}&lng={lng}&start={start}&size={size}")
  List<Crag> searchCrags(@Param("lng") Double longitude,
                         @Param("lat") Double latitude,
                         @Param("start") int start,
                         @Param("size") int size);

  @RequestLine("PUT " + CRAGS_ENDPOINT + "/")
  String update(Crag c);

  @RequestLine("PUT " + ROUTES_ENDPOINT + "/")
  String update(Route r);
}
