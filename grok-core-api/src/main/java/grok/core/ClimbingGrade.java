package grok.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;

/**
 * @see <a href="http://www.rockclimbing.com/Articles/Introduction_to_Climbing/Difficulty_Grades_and_Conversions_529.html"></a>
 */
@AutoValue
public abstract class ClimbingGrade implements Comparable<ClimbingGrade> {
  public enum Country {
    USA, FRANCE, AUSTRALIA, SOUTH_AFRICA, GERMANY, UNITED_KINGDOM;
  }

  public static ClimbingGrade usa(String value) {
    return of(Country.USA, value);
  }

  public static ClimbingGrade of(Country country, String value) {
    for (ClimbingGrade maybe : KNOWN) {
      if (maybe.matches(country, value)) {
        return maybe;
      }
    }
    throw new IllegalArgumentException(value + " does not exist for " + country);
  }

  // TODO(achaphiv): expose?
  @JsonCreator
  static ClimbingGrade of(Map<Country, Set<String>> values) {
    for (ClimbingGrade maybe : KNOWN) {
      if (maybe.valuesMap().equals(values)) {
        return maybe;
      }
    }
    throw new IllegalArgumentException(values + " is not a valid combination");
  }

  //@formatter:off
  private static final List<ClimbingGrade> KNOWN = Arrays.asList(
      known("5.1  ", "2     ", "7    ", "8    ", "III-      ", "M        "),
      known("5.2  ", "2+    ", "8    ", "9    ", "III       ", "D        "),
      known("5.3  ", "3     ", "9/10 ", "10   ", "III+      ", "VD 3a    "),
      known("5.4  ", "3+    ", "11   ", "12   ", "IV-       ", "VD/HVD 3b"),
      known("5.5  ", "4     ", "12   ", "13   ", "IV        ", "HVD/S 3c "),
      known("5.6  ", "4+    ", "13   ", "14   ", "IV+/V-    ", "MS 4a    "),
      known("5.7  ", "5a    ", "14/15", "15   ", "V-/V      ", "S/HS 4b  "),
      known("5.8  ", "5b    ", "15/16", "16   ", "V+/VI-    ", "HS/VS 4b "),
      known("5.9  ", "5c    ", "17   ", "17/18", "VI-/VI    ", "HVS 4c   "),
      known("5.10a", "6a    ", "18   ", "19   ", "VI/VI+    ", "HVS 5a   "),
      known("5.10b", "6a+   ", "19   ", "20   ", "VII-      ", "E1 5a    "),
      known("5.10c", "6b    ", "20   ", "21   ", "VII-/VII  ", "E1 5b    "),
      known("5.10d", "6b+   ", "20/21", "22   ", "VII/VII+  ", "E2 5b    "),
      known("5.11a", "6c    ", "21   ", "22/23", "VII+      ", "E2 5c    "),
      known("5.11b", "6c/6c+", "22   ", "23/24", "VIII-     ", "E3 5c    "),
      known("5.11c", "6c+   ", "22/23", "24   ", "VIII      ", "E3 6a    "),
      known("5.11d", "7a    ", "23   ", "25   ", "VIII/VIII+", "E4 6a    "),
      known("5.12a", "7a+   ", "24   ", "26   ", "VIII+     ", "E4 6b    "),
      known("5.12b", "7b    ", "25   ", "27   ", "IX-       ", "E5 6b    "),
      known("5.12c", "7b+   ", "26   ", "28   ", "IX-/IX    ", "E5/E6 6b "),
      known("5.12d", "7c    ", "27   ", "29   ", "IX/IX+    ", "E6 6b    "),
      known("5.13a", "7c+   ", "28   ", "30   ", "IX+       ", "E6 6c    "),
      known("5.13b", "8a    ", "29   ", "31   ", "X-        ", "E7 6c    "),
      known("5.13c", "8a+   ", "30   ", "32   ", "X-/X      ", "E7 7a    "),
      known("5.13d", "8b    ", "31   ", "33   ", "X/X+      ", "E8 7a    "),
      known("5.14a", "8b+   ", "32   ", "34   ", "X+        ", "E8 7b    "),
      known("5.14b", "8c    ", "33   ", "35   ", "XI-       ", "E9 7b    "),
      known("5.14c", "8c+   ", "34   ", "36   ", "XI        ", "E10 7b   "),
      known("5.14d", "9a    ", "35   ", "37   ", "XI+       ", "E10 7c   "),
      known("5.15a", "9a+   ", "36   ", "38   ", "XI+/XII-  ", "E11 7c   "),
      known("5.15b", "9b    ", "37   ", "39   ", "XII-/XII  ", "E11 8a   "),
      known("5.15c", "9b+   ", "38   ", "40   ", "XII       ", "E11 8b   "),
      known("5.15d", "9c    ", "39   ", "41   ", "XII+      ", "E11 8c   "));
  //@formatter:on

  private static ClimbingGrade known(String usa, String france, String australia,
                                     String southAfrica, String germany, String uk) {
    return new AutoValue_ClimbingGrade(ImmutableSetMultimap.<Country, String> builder()
                                                           .putAll(Country.USA, split(usa))
                                                           .putAll(Country.FRANCE, split(france))
                                                           .putAll(Country.AUSTRALIA, split(australia))
                                                           .putAll(Country.SOUTH_AFRICA, split(southAfrica))
                                                           .putAll(Country.GERMANY, split(germany))
                                                           .putAll(Country.UNITED_KINGDOM, split(uk))
                                                           .build());
  }

  private static String[] split(String value) {
    return value.trim().split("/");
  }

  ClimbingGrade() {}

  abstract SetMultimap<Country, String> values();

  // TODO(achaphiv): expose?
  @JsonValue
  Map<Country, Set<String>> valuesMap() {
    return Multimaps.asMap(values());
  }

  private boolean matches(Country country, String value) {
    return values().get(country).contains(value);
  }

  @Override
  public int compareTo(ClimbingGrade other) {
    return Integer.compare(KNOWN.indexOf(this), KNOWN.indexOf(other));
  }
}
