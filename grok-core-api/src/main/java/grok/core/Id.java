package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Id {

  @JsonCreator
  public static Id of(@JsonProperty String id) {
    return new AutoValue_Id(id);
  }

  @JsonValue
  public abstract String id();
}
