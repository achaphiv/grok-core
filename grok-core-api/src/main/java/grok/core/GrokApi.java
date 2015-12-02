package grok.core;

public final class GrokApi {
  public static final String CRAGS_ENDPOINT = "/crags";
  public static final String CRAGS_ID_ENDPOINT = "/crags/{id}";
  public static final String CRAGS_ID_ROUTES_ENDPOINT = "/crags/{id}/routes";
  public static final String ROUTES_ENDPOINT = "/routes";
  public static final String ROUTES_ID_ENDPOINT = "/routes/{id}";
  public static final String ROUTES_WITH_CRAGS_ENDPOINT = "/routes/withCrag";
  public static final String ROUTES_WITH_CRAGS_ID_ENDPOINT = "/routes/{id}/withCrag";
  public static final int DEFAULT_PAGE_SIZE = 10;

  private GrokApi() {}
}
