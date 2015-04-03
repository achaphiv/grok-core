package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Rating {
  @JsonCreator
  public static Rating of(int value) {
    return new AutoValue_Rating(value);
  }

  Rating() {}

  @JsonValue
  public abstract int value();
}
