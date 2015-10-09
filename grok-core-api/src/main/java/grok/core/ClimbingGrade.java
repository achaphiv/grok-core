package grok.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimaps;

/**
 * @see <a href="http://en.wikipedia.org/wiki/Grade_%28climbing%29"></a>
 * @see <a href="http://www.rockclimbing.com/Articles/Introduction_to_Climbing/Difficulty_Grades_and_Conversions_529.html"></a>
 */
@AutoValue
public abstract class ClimbingGrade implements Comparable<ClimbingGrade> {
  public enum System {
    /**
     * <a href="http://en.wikipedia.org/wiki/Yosemite_Decimal_System">Yosemite Decimal System</a>
     * <p>
     * Used in USA and Canada.
     */
    YDS,
    /**
     * Used in the vast majority of European countries and in many international
     * events outside the USA.
     */
    FRENCH,
    /**
     * Used in Australia and New Zealand.
     */
    EWBANK,
    /**
     * Used in South Africa.
     */
    EWBANK_SOUTH_AFRICA,
    /**
     * Used in Germany, Austria, Switzerland, Czech Republic, Slovakia, and
     * Hungary.
     */
    UIAA,
    /**
     * A.K.A. UK grading system.
     * <p>
     * Used in Great Britain and Ireland.
     */
    BRITISH;

    /**
     * E.G. YDS.possibleValues() = { 5.1, 5.2, 5.3, ..., 5.15d }
     */
    public ImmutableSet<String> possibleValues() {
      ImmutableSet.Builder<String> known = ImmutableSet.builder();
      for (ClimbingGrade grade : KNOWN) {
        known.add(grade.valueIn(this));
      }
      return known.build();
    }
  }

  public static ClimbingGrade yds(String value) {
    return of(value, System.YDS);
  }

  public static ClimbingGrade french(String value) {
    return of(value, System.FRENCH);
  }

  public static ClimbingGrade of(String value, System system) {
    for (ClimbingGrade maybe : KNOWN) {
      if (maybe.matches(system, value)) {
        return maybe;
      }
    }
    throw new IllegalArgumentException(value + " does not exist for " + system);
  }

  // TODO(achaphiv): expose?
  @JsonCreator
  static ClimbingGrade of(Map<System, Set<String>> values) {
    for (ClimbingGrade maybe : KNOWN) {
      if (maybe.valuesMap().equals(values)) {
        return maybe;
      }
    }
    throw new IllegalArgumentException(values + " is not a valid combination");
  }

  //@formatter:off
  private static final List<ClimbingGrade> KNOWN = Arrays.asList(
      known("5.1  ", "2         ", "7    ", "8    ", "III-      ", "M        "),
      known("5.2  ", "2+        ", "8    ", "9    ", "III       ", "D        "),
      known("5.3  ", "3/3a/3b/3c", "9/10 ", "10   ", "III+      ", "VD 3a    "),
      known("5.4  ", "4a        ", "11   ", "12   ", "IV-       ", "VD/HVD 3b"),
      known("5.5  ", "4b        ", "12   ", "13   ", "IV        ", "HVD/S 3c "),
      known("5.6  ", "4c        ", "13   ", "14   ", "IV+/V-    ", "MS 4a    "),
      known("5.7  ", "5a        ", "14/15", "15   ", "V-/V      ", "S/HS 4b  "),
      known("5.8  ", "5b        ", "15/16", "16   ", "V+/VI-    ", "HS/VS 4b "),
      known("5.9  ", "5c        ", "17   ", "17/18", "VI-/VI    ", "HVS 4c   "),
      known("5.10a", "6a        ", "18   ", "19   ", "VI/VI+    ", "HVS 5a   "),
      known("5.10b", "6a+       ", "19   ", "20   ", "VII-      ", "E1 5a    "),
      known("5.10c", "6b        ", "20   ", "21   ", "VII-/VII  ", "E1 5b    "),
      known("5.10d", "6b+       ", "20/21", "22   ", "VII/VII+  ", "E2 5b    "),
      known("5.11a", "6c        ", "21   ", "22/23", "VII+      ", "E2 5c    "),
      known("5.11b", "6c/6c+    ", "22   ", "23/24", "VIII-     ", "E3 5c    "),
      known("5.11c", "6c+       ", "22/23", "24   ", "VIII      ", "E3 6a    "),
      known("5.11d", "7a        ", "23   ", "25   ", "VIII/VIII+", "E4 6a    "),
      known("5.12a", "7a+       ", "24   ", "26   ", "VIII+     ", "E4 6b    "),
      known("5.12b", "7b        ", "25   ", "27   ", "IX-       ", "E5 6b    "),
      known("5.12c", "7b+       ", "26   ", "28   ", "IX-/IX    ", "E5/E6 6b "),
      known("5.12d", "7c        ", "27   ", "29   ", "IX/IX+    ", "E6 6b    "),
      known("5.13a", "7c+       ", "28   ", "30   ", "IX+       ", "E6 6c    "),
      known("5.13b", "8a        ", "29   ", "31   ", "X-        ", "E7 6c    "),
      known("5.13c", "8a+       ", "30   ", "32   ", "X-/X      ", "E7 7a    "),
      known("5.13d", "8b        ", "31   ", "33   ", "X/X+      ", "E8 7a    "),
      known("5.14a", "8b+       ", "32   ", "34   ", "X+        ", "E8 7b    "),
      known("5.14b", "8c        ", "33   ", "35   ", "XI-       ", "E9 7b    "),
      known("5.14c", "8c+       ", "34   ", "36   ", "XI        ", "E10 7b   "),
      known("5.14d", "9a        ", "35   ", "37   ", "XI+       ", "E10 7c   "),
      known("5.15a", "9a+       ", "36   ", "38   ", "XI+/XII-  ", "E11 7c   "),
      known("5.15b", "9b        ", "37   ", "39   ", "XII-/XII  ", "E11 8a   "),
      known("5.15c", "9b+       ", "38   ", "40   ", "XII       ", "E11 8b   "),
      known("5.15d", "9c        ", "39   ", "41   ", "XII+      ", "E11 8c   ")
  );
  //@formatter:on

  private static ClimbingGrade known(String usa, String france,
                                     String ewbank, String ewbankSouthAfrica,
                                     String uiaa, String british) {
    return new AutoValue_ClimbingGrade(ImmutableSetMultimap.<System, String> builder()
                                                           .putAll(System.YDS, split(usa))
                                                           .putAll(System.FRENCH, split(france))
                                                           .putAll(System.EWBANK, split(ewbank))
                                                           .putAll(System.EWBANK_SOUTH_AFRICA, split(ewbankSouthAfrica))
                                                           .putAll(System.UIAA, split(uiaa))
                                                           .putAll(System.BRITISH, split(british))
                                                           .build());
  }

  private static String[] split(String value) {
    return value.trim().split("/");
  }

  ClimbingGrade() {}

  abstract ImmutableSetMultimap<System, String> values();

  public String valueIn(System system) {
    return Joiner.on("/").join(values().get(system));
  }

  // TODO(achaphiv): expose?
  @JsonValue
  Map<System, Set<String>> valuesMap() {
    return Multimaps.asMap(values());
  }

  private boolean matches(System country, String value) {
    return values().get(country).contains(value);
  }

  @Override
  public int compareTo(ClimbingGrade other) {
    return Integer.compare(KNOWN.indexOf(this), KNOWN.indexOf(other));
  }
}
