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
    return User.create()
        .id(id)
        .name(name)
        .location(location)
        .email(email)
        .build();
  }

  public static Builder create() {
    return AutoValue_User.create();
  }

  User() {}

  @JsonProperty
  @Nullable
  public abstract String id();

  @JsonProperty
  public abstract String name();

  @JsonProperty
  public abstract String location();

  @JsonProperty
  public abstract String email();

  @AutoValue.Builder
  public abstract static class Builder {
    Builder() {}

    public abstract Builder id(String value);

    public abstract Builder name(String value);

    public abstract Builder location(String value);

    public abstract Builder email(String value);

    public abstract User build();
  }
}
