package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Optional;

public interface Grade {
  @JsonCreator
  static Grade from(String value) {
    Optional<BoulderingGrade> maybe = BoulderingGrade.fromValue(value);
    if (maybe.isPresent()) {
      return maybe.get();
    }
    Optional<RopeGrade> maybeRope = RopeGrade.fromValue(value);
    if (maybeRope.isPresent()) {
      return maybeRope.get();
    }
    try {
      return new FrenchGrade(value);
    } catch (IllegalArgumentException ignored) {}

    throw new IllegalArgumentException(value + " is not a recognized grade");
  }

  static boolean isGrade(String value) {
    return BoulderingGrade.fromValue(value).isPresent()
        || RopeGrade.fromValue(value).isPresent()
        || FrenchGrade.isValid(value);
  }

  @JsonValue
  String getValue();
}
