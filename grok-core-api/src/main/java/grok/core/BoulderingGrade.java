package grok.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;

/**
 * @see <a href="http://en.wikipedia.org/wiki/Grade_%28bouldering%29"></a>
 * @see <a href="http://en.wikipedia.org/wiki/Grade_%28climbing%29"></a>
 * @see <a href="http://www.climbing.co.za/wp-content/uploads/2012/10/climbza_gradechart.png"></a>
 */
@AutoValue
public abstract class BoulderingGrade implements Comparable<BoulderingGrade> {
  public enum System {
    /**
     * A.K.A. "V" Scale. Used in USA.
     */
    HUECO,
    /**
     * Originated in France. Used mainly in Europe.
     */
    FONTAINEBLEAU,
    /**
     * The B-System conceived by John Gill in the 1950s.
     */
    B;
  }

  public static BoulderingGrade of(String value, System system) {
    for (BoulderingGrade maybe : KNOWN) {
      if (maybe.matches(system, value)) {
        return maybe;
      }
    }
    throw new IllegalArgumentException(value + " does not exist for " + system);
  }

  // TODO(achaphiv): expose?
  @JsonCreator
  static BoulderingGrade of(Map<System, Set<String>> values) {
    for (BoulderingGrade maybe : KNOWN) {
      if (maybe.valuesMap().equals(values)) {
        return maybe;
      }
    }
    throw new IllegalArgumentException(values + " is not a valid combination");
  }

  //@formatter:off
  private static final List<BoulderingGrade> KNOWN = Arrays.asList(
      known("V0-/V0 ", "1/2/3   ", "B1 "),
      known("V1     ", "4-/4+   ", "B2 "),
      known("V2     ", "5a/5b/5c", "B3 "),
      known("V3     ", "6a/6b   ", "B4 "),
      known("V4     ", "6c      ", "B5 "),
      known("V5     ", "6c+     ", "B6 "),
      known("V6     ", "7a      ", "B7 "),
      known("V7     ", "7a+     ", "B8 "),
      known("V8     ", "7b/7b+  ", "B9 "),
      known("V9     ", "7c      ", "B10"),
      known("V10    ", "7c+     ", "B11"),
      known("V11/V12", "8a/8a+  ", "B12"),
      known("V13    ", "8b      ", "B13"),
      known("V14    ", "8b+     ", "B14"),
      known("V15    ", "8c      ", "B15")
  );
  //@formatter:on

  private static BoulderingGrade known(String hueco, String font, String b) {
    return new AutoValue_BoulderingGrade(ImmutableSetMultimap.<System, String> builder()
                                                             .putAll(System.HUECO, split(hueco))
                                                             .putAll(System.FONTAINEBLEAU, split(font))
                                                             .putAll(System.B, split(b))
                                                             .build());
  }

  private static String[] split(String value) {
    return value.trim().split("/");
  }

  BoulderingGrade() {}

  abstract SetMultimap<System, String> values();

  public String valueIn(System system) {
    return Joiner.on("/").join(values().get(system));
  }

  // TODO(achaphiv): expose?
  @JsonValue
  Map<System, Set<String>> valuesMap() {
    return Multimaps.asMap(values());
  }

  private boolean matches(System system, String value) {
    return values().get(system).contains(value);
  }

  @Override
  public int compareTo(BoulderingGrade other) {
    return Integer.compare(KNOWN.indexOf(this), KNOWN.indexOf(other));
  }
}
