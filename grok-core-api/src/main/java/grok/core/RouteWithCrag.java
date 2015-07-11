package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RouteWithCrag {

  @JsonCreator
  public static RouteWithCrag of(@JsonProperty("route") Route r, @JsonProperty("crag") Crag c) {
    return new AutoValue_RouteWithCrag(r, c);
  }

  RouteWithCrag() {}

  @JsonProperty
  public abstract Route route();

  @JsonProperty
  public abstract Crag crag();
}
