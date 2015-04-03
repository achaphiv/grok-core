package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import org.geojson.GeoJsonObject;
import org.mongojack.Id;

import javax.annotation.Nullable;

@AutoValue
public abstract class Crag {
  @JsonCreator
  public static Crag of(@Id @JsonProperty("id") Long id,
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

  public static AutoValue_Crag.Builder_AutoValue_Crag create() {
    return AutoValue_Crag.builder();
  }

  @Id
  @JsonProperty
  public abstract long getId();

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
  public abstract long getRegionId();

  public static String COLLECTION = "crags";
}
