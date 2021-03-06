package grok.core;

import javax.annotation.Nullable;

import org.geojson.GeoJsonObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonInclude(Include.NON_NULL)
public abstract class Route {
  @JsonCreator
  public static Route of(@JsonProperty("id") Id id,
                         @JsonProperty("cragId") Id cragId,
                         @JsonProperty("name") String name,
                         @JsonProperty("grade") ClimbingGrade grade,
                         @JsonProperty("grokRating") Rating rating,
                         @JsonProperty("thumbsDown") Count thumbsDown,
                         @JsonProperty("thumbsUp") Count thumbsUp,
                         @JsonProperty("location") GeoJsonObject location) {
    return create().id(id)
                   .cragId(cragId)
                   .name(name)
                   .grade(grade)
                   .thumbsDown(thumbsDown)
                   .thumbsUp(thumbsUp)
                   .grokRating(rating)
                   .searchIndex(name + " " + grade)
                   .location(location)
                   .build();
  }

  public static Builder create() {
    return new AutoValue_Route.Builder();
  }

  Route() {}

  @JsonProperty
  @Nullable
  public abstract Id getId();

  @JsonProperty
  public abstract Id getCragId();

  @JsonProperty
  public abstract String getName();

  @JsonProperty
  public abstract ClimbingGrade getGrade();

  @JsonProperty
  public abstract Rating getGrokRating();

  @JsonProperty
  @Nullable
  public abstract Image getImage();

  @JsonProperty
  public abstract Count getThumbsUp();

  @JsonProperty
  public abstract Count getThumbsDown();

  @JsonProperty
  @Nullable
  public abstract String getSearchIndex();

  @JsonProperty
  @Nullable
  public abstract GeoJsonObject getLocation();

  public abstract Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    Builder() {}

    public abstract Builder id(Id value);

    public abstract Builder cragId(Id value);

    public abstract Builder name(String value);

    public abstract Builder grade(ClimbingGrade value);

    public abstract Builder grokRating(Rating value);

    public abstract Builder image(Image value);

    public abstract Builder thumbsUp(Count value);

    public abstract Builder thumbsDown(Count value);

    public abstract Builder searchIndex(String value);

    public abstract Builder location(GeoJsonObject value);

    public abstract Route build();
  }
}
