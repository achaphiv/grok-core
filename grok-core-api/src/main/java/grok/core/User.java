package grok.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {

  public static User of(long id, String name) {
    return new AutoValue_User(id, name);
  }

  @JsonProperty
  public abstract long id();

  @JsonProperty
  public abstract String name();
}
