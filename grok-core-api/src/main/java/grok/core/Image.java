package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.mongojack.ObjectId;

@AutoValue
public abstract class Image {
  @JsonCreator
  public static Image of(@JsonProperty("id") String id,
                         @JsonProperty("title") String title,
                         @JsonProperty("url") String url) {
    return builder().id(id)
                    .title(title)
                    .url(url)
                    .build();
  }

  public static AutoValue_Image.Builder_AutoValue_Image builder() {
    return AutoValue_Image.builder();
  }

  @ObjectId
  @JsonProperty
  public abstract String id();

  @JsonProperty
  public abstract String title();

  @JsonProperty
  public abstract String url();
}
