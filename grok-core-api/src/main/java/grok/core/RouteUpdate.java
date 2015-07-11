package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.mongojack.Id;

import javax.annotation.Nullable;
import java.util.Date;

@AutoValue
public abstract class RouteUpdate {
  @JsonCreator
  public static RouteUpdate of(
      @Id @JsonProperty("id") String id,
      @JsonProperty("client") String clientId,
      @JsonProperty("date") Date date,
      @JsonProperty("route") Route route) {
    return new AutoValue_RouteUpdate(id, clientId, date, route);
  }

  RouteUpdate() {}

  @Id
  @Nullable
  @JsonProperty
  public abstract String id();

  @JsonProperty
  public abstract String clientId();

  @JsonProperty
  public abstract Date date();

  @JsonProperty
  public abstract Route route();
}
