package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Id {
  @JsonCreator
  public static Id of(String id) {
    return new AutoValue_Id(id);
  }

  /**
   * Use {@link #of(String)} instead. This is here for Jersey deserialization.
   */
  @Deprecated
  public static Id valueOf(String id) {
    return of(id);
  }

  Id() {}

  @Deprecated
  public String id() {
    return value();
  }

  @JsonValue
  public abstract String value();

  @Override
  public String toString() {
    return value();
  }
}
