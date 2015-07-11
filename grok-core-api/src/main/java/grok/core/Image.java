package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

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

  public static Builder builder() {
    return new AutoValue_Image.Builder();
  }

  Image() {}

  @JsonProperty
  public abstract String id();

  @JsonProperty
  public abstract String title();

  @JsonProperty
  public abstract String url();

  @AutoValue.Builder
  public abstract static class Builder {
    Builder() {}

    public abstract Builder id(String value);

    @JsonProperty
    public abstract Builder title(String value);

    @JsonProperty
    public abstract Builder url(String value);

    public abstract Image build();
  }
}
