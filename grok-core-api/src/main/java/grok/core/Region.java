package grok.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.mongojack.Id;

@AutoValue
public abstract class Region {
  public static final String COLLECTION = "regions";

  public static AutoValue_Region.Builder_AutoValue_Region create() {
    return AutoValue_Region.builder();
  }

  @Id
  @JsonProperty
  public abstract long getId();

  @JsonProperty
  public abstract String getName();

  @JsonProperty
  public abstract String getImage();
}
