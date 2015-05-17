package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

@AutoValue
public abstract class User {

  @JsonCreator
  public static User of(@JsonProperty("_id") String id,
                        @JsonProperty("name") String name,
                        @JsonProperty("location") String location,
                        @JsonProperty("email") String email) {
    return new AutoValue_User(id, name, location, email);
  }

  @JsonProperty
  @Nullable
  public abstract String id();

  @JsonProperty
  public abstract String name();

  @JsonProperty
  public abstract String location();

  @JsonProperty
  public abstract String email();
}
