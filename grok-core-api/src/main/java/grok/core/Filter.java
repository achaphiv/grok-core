package grok.core;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingMap;

@Beta
public final class Filter extends ForwardingMap<String, Object> {
  public static final String QUERY = "query";
  public static final String CRAG = "crag";
  public static final String PAGE = "page";
  public static final String PER_PAGE = "per_page";
  public static final String LATITUDE = "lat";
  public static final String LONGITUDE = "lng";

  public static final Filter EMPTY = new Filter();

  private final Map<String, Object> params;

  public Filter() {
    this(new HashMap<String, Object>());
  }

  public Filter(Map<String, Object> params) {
    this.params = params;
  }

  // @formatter:off
  @QueryParam(QUERY) public Filter setQuery(String value) { return set(QUERY, value); }
  @QueryParam(CRAG) public Filter setCrag(Id value) { return set(CRAG, value); }
  @QueryParam(PAGE) public Filter setPage(String value) { return set(PAGE, value); }
  @QueryParam(PER_PAGE) @DefaultValue(GrokApi.DEFAULT_PAGE_SIZE + "") public Filter setPerPage(Integer value) { return set(PER_PAGE, value); }
  @QueryParam(LATITUDE) public Filter setLatitude(Double value) { return set(LATITUDE, value); }
  @QueryParam(LONGITUDE) public Filter setLongitude(Double value) { return set(LONGITUDE, value); }
  
  public String getQuery() { return get(QUERY); }
  public Id getCrag() { return get(CRAG); }
  public String getPage() { return get(PAGE); }
  public Integer getPerPage() { return get(PER_PAGE); }
  public Double getLatitude() { return get(LATITUDE); }
  public Double getLongitude() { return get(LONGITUDE); }
  // @formatter:on

  private Filter set(String key, Object value) {
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

  public Filter copy() {
    return new Filter(new HashMap<>(params));
  }
}
