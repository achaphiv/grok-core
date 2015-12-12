package grok.core.retrofit;

import static grok.core.GrokApi.CRAGS_ENDPOINT;
import static grok.core.GrokApi.CRAGS_ID_ENDPOINT;
import static grok.core.GrokApi.CRAGS_ID_ROUTES_ENDPOINT;
import static grok.core.GrokApi.ROUTES_ENDPOINT;
import static grok.core.GrokApi.ROUTES_ID_ENDPOINT;
import static grok.core.GrokApi.ROUTES_WITH_CRAGS_ENDPOINT;
import static grok.core.GrokApi.ROUTES_WITH_CRAGS_ID_ENDPOINT;

import java.util.List;

import grok.core.Crag;
import grok.core.Id;
import grok.core.Filter;
import grok.core.Route;
import grok.core.RouteWithCrag;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import retrofit.http.Url;

public interface Grok {
  String BASE_URL = "https://peaceful-falls-5210.herokuapp.com/";
  String BASE_URL_ALTERNATE = "https://server-grokclimbing.rhcloud.com/";

  @GET(CRAGS_ENDPOINT)
  Call<List<Crag>> crags();

  @GET(CRAGS_ENDPOINT)
  Call<List<Crag>> crags(@QueryMap Filter filter);

  @GET
  Call<List<Crag>> moreCrags(@Url String paginationUrl);

  @POST(CRAGS_ENDPOINT)
  @Headers("Content-Type: application/json")
  Call<Crag> insert(Crag c);

  @GET(CRAGS_ID_ENDPOINT)
  Call<Crag> crag(@Path("id") Id id);

  @GET(CRAGS_ID_ENDPOINT)
  @Headers("Content-Type: application/json")
  Call<Crag> update(@Path("id") Id id, Crag c);

  @GET(CRAGS_ID_ROUTES_ENDPOINT)
  Call<List<Route>> cragRoutes(@Path("id") Id id);

  @GET(CRAGS_ID_ROUTES_ENDPOINT)
  Call<List<Route>> cragRoutes(@Path("id") Id id, @QueryMap Filter filter);

  @PUT(ROUTES_ID_ENDPOINT)
  Call<Route> route(@Path("id") Id id);

  @PUT(ROUTES_ID_ENDPOINT)
  @Headers("Content-Type: application/json")
  Call<Route> update(@Path("id") Id id, Route r);

  @GET(ROUTES_ENDPOINT)
  Call<List<Route>> routes();

  @GET(ROUTES_ENDPOINT)
  Call<List<Route>> routes(@QueryMap Filter filter);

  @GET
  Call<List<Route>> moreRoutes(@Url String paginationUrl);

  @POST(ROUTES_ENDPOINT)
  @Headers("Content-Type: application/json")
  Call<Route> insert(Route r);

  /**
   * @deprecated Use {@link #cragRoutes(Id, Filter))} instead
   */
  @Deprecated
  @GET(ROUTES_WITH_CRAGS_ENDPOINT)
  Call<List<RouteWithCrag>> routeWithCrags(@QueryMap Filter filter);

  /**
   * @deprecated Use {@link #cragRoutes(Id)} instead
   */
  @Deprecated
  @GET(ROUTES_WITH_CRAGS_ID_ENDPOINT)
  Call<RouteWithCrag> routeWithCrag(@Path("id") Id id);
}
