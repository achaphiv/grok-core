package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.Date;

@AutoValue
public abstract class RouteUpdate {

  @JsonCreator
  public static RouteUpdate of(String clientId, Date date, Route route) {
    return new AutoValue_RouteUpdate(clientId, date, route);
  }

  @JsonProperty
  public abstract String clientId();

  @JsonProperty
  public abstract Date date();

  @JsonProperty
  public abstract Route route();
}
