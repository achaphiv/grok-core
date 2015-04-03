package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Count {
  @JsonCreator
  public static Count of(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Count cannot be negative");
    }
    return new AutoValue_Count(value);
  }

  Count() {}

  @JsonValue
  public abstract int value();
}
