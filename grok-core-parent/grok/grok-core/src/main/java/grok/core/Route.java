package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.mongojack.Id;

import javax.annotation.Nullable;

@AutoValue
public abstract class Route {
  public static final String COLLECTION = "routes";
  public static final String SEARCH_INDEX = "searchIndex";

  @JsonCreator
  public static Route of(@Id @JsonProperty("id") Long id,
                         @JsonProperty("cragId") Long cragId,
                         @JsonProperty("name") String name,
                         @JsonProperty("grade") Grade grade,
                         @JsonProperty("grokRating") int rating,
                         @JsonProperty("thumbsDown") int thumbsDown,
                         @JsonProperty("thumbsUp") int thumbsUp) {
    return create().id(id)
                   .cragId(cragId)
                   .name(name)
                   .grade(grade)
                   .thumbsDown(thumbsDown)
                   .thumbsUp(thumbsUp)
                   .grokRating(rating)
                   .searchIndex(name + " " + grade.getValue())
                   .build();
  }

  public static AutoValue_Route.Builder_AutoValue_Route create() {
    return AutoValue_Route.builder();
  }

  @Id
  @JsonProperty
  public abstract Long getId();

  @JsonProperty
  public abstract Long getCragId();

  @JsonProperty
  public abstract String getName();

  @JsonProperty
  public abstract Grade getGrade();

  @JsonProperty
  public abstract int getGrokRating();

  @JsonProperty
  @Nullable
  public abstract String getImage();

  @JsonProperty
  public abstract int getThumbsUp();

  @JsonProperty
  public abstract int getThumbsDown();

  @JsonProperty
  @Nullable
  public abstract String getSearchIndex();
}
