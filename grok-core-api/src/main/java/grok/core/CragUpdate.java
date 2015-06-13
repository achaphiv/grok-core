package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.Date;

@AutoValue
public abstract class CragUpdate {

  @JsonCreator
  public static CragUpdate of(String clientId, Date date, Crag crag) {
    return new AutoValue_CragUpdate(clientId, date, crag);
  }

  @JsonProperty
  public abstract String clientId();
  @JsonProperty
  public abstract Date date();
  @JsonProperty
  public abstract Crag crag();
}
