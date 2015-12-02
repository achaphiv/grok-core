package grok.core;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingMap;

@Beta
public final class Query extends ForwardingMap<String, Object> {
  public static final String QUERY = "query";
  public static final String CRAG = "crag";
  public static final String PAGE = "page";
  public static final String PER_PAGE = "per_page";
  public static final String LATITUDE = "lat";
  public static final String LONGITUDE = "lng";

  public static final Query EMPTY = new Query();

  private final Map<String, Object> params;

  public Query() {
    this(new HashMap<String, Object>());
  }

  public Query(Map<String, Object> params) {
    this.params = params;
  }

  // @formatter:off
  @QueryParam(QUERY) public Query setQuery(String value) { return set(QUERY, value); }
  @QueryParam(CRAG) public Query setCrag(Id value) { return set(CRAG, value); }
  @QueryParam(PAGE) public Query setPage(String value) { return set(PAGE, value); }
  @QueryParam(PER_PAGE) @DefaultValue(GrokApi.DEFAULT_PAGE_SIZE + "") public Query setPerPage(Integer value) { return set(PER_PAGE, value); }
  @QueryParam(LATITUDE) public Query setLatitude(Double value) { return set(LATITUDE, value); }
  @QueryParam(LONGITUDE) public Query setLongitude(Double value) { return set(LONGITUDE, value); }
  
  public String getQuery() { return get(QUERY); }
  public Id getCrag() { return get(CRAG); }
  public String getPage() { return get(PAGE); }
  public Integer getPerPage() { return get(PER_PAGE); }
  public Double getLatitude() { return get(LATITUDE); }
  public Double getLongitude() { return get(LONGITUDE); }
  // @formatter:on

  private Query set(String key, Object value) {
    delegate().put(key, value);
    return this;
  }

  @SuppressWarnings("unchecked")
  private <T> T get(String key) {
    return (T) delegate().get(key);
  }

  @Override
  protected Map<String, Object> delegate() {
    return params;
  }

  public Query withPage(String value) {
    Query q = new Query(params);
    q.setPage(value);
    return q;
  }
}
