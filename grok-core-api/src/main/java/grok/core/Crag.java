package grok.core;

import javax.annotation.Nullable;

import org.geojson.GeoJsonObject;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Crag {
  @JsonCreator
  public static Crag of(@ObjectId @JsonProperty("id") Id id,
                        @JsonProperty("name") String name,
                        @JsonProperty("country") String country,
                        @JsonProperty("city") String city,
                        @JsonProperty("state") String state,
                        @JsonProperty("loc") GeoJsonObject location) {
    return create().id(id)
                   .name(name)
                   .country(country)
                   .city(city)
                   .state(state)
                   .location(location)
                   .build();
  }

  public static Builder create() { 
    return new AutoValue_Crag.Builder();
  }

  Crag() {}

  @ObjectId
  @JsonProperty("id")
  @Nullable
  public abstract Id getId();

  @JsonProperty
  public abstract String getName();

  @JsonProperty
  @Nullable
  public abstract String getCountry();

  @JsonProperty
  @Nullable
  public abstract String getCity();

  @JsonProperty
  @Nullable
  public abstract String getState();

  @JsonProperty
  @Nullable
  public abstract GeoJsonObject getLocation();

  @JsonProperty
  @Nullable
  public abstract Image getImage();

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    Builder() {}

    public abstract Builder id(Id value);

    public abstract Builder name(String value);

    public abstract Builder country(String value);

    public abstract Builder city(String value);

    public abstract Builder state(String value);

    public abstract Builder location(GeoJsonObject value);

    public abstract Builder image(Image image);

    public abstract Crag build();
  }
}
