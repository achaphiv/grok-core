package grok.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import org.mongojack.Id;

@AutoValue
public abstract class Region {

  public static Builder create() {
    return new AutoValue_Region.Builder();
  }

  Region() {}

  @Id
  @JsonProperty
  public abstract long getId();

  @JsonProperty
  public abstract String getName();

  @JsonProperty
  public abstract String getImage();

  @AutoValue.Builder
  public abstract static class Builder {
    Builder() {}

    public abstract Builder id(long value);

    public abstract Builder name(String value);

    public abstract Builder image(String value);

    public abstract Region build();
  }
}
