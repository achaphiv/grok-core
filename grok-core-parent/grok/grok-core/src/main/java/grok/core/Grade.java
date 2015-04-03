package grok.core;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface Grade {
  @JsonCreator
  static Grade from(String toParse) {
    Optional<BoulderingGrade> maybe = BoulderingGrade.fromValue(toParse);
    if (maybe.isPresent()) {
      return maybe.get();
    }
    Optional<RopeGrade> maybeRope = RopeGrade.fromValue(toParse);
    if (maybeRope.isPresent()) {
      return maybeRope.get();
    }
    try {
      return new FrenchGrade(toParse);
    } catch (IllegalArgumentException ignored) {}

    throw new IllegalArgumentException(toParse + " is not a recognized grade");
  }

  static boolean isGrade(String toParse) {
    return BoulderingGrade.fromValue(toParse).isPresent()
        || RopeGrade.fromValue(toParse).isPresent()
        || FrenchGrade.isValid(toParse);
  }

  @JsonValue
  String getValue();
}
