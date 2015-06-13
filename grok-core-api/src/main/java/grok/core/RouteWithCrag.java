package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RouteWithCrag {

  @JsonCreator
  public static RouteWithCrag of(Route r, Crag c) {
    return new AutoValue_RouteWithCrag(r, c);
  }

  @JsonProperty
  public abstract Route route();

  @JsonProperty
  public abstract Crag crag();
}