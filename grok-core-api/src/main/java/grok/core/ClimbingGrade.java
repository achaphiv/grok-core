package grok.core;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

/**
 * @see <a href="http://www.mec.ca/AST/ContentPrimary/Learn/Climbing/IntroToClimbing/ClimbingGradeConversion.jsp"></a>
 */
public final class ClimbingGrade {
  enum Country {
    USA, FRANCE, AUSTRALIA, SOUTH_AFRICA, GERMANY, UNITED_KINGDOM;
  }

  private static final class DataTable {
    private final Table<Integer, Country, String> known = TreeBasedTable.create();

    private int rowNumber;

    DataTable() {
      //@formatter:off
      put("5.1",   "2",      "7",     "8",     "III-",       "M");
      put("5.2",   "2+",     "8",     "9",     "III",        "D");
      put("5.3",   "3",      "9/10",  "10",    "III+",       "VD 3a");
      put("5.4",   "3+",     "11",    "12",    "IV-",        "VD/HVD 3b");
      put("5.5",   "4",      "12",    "13",    "IV",         "HVD/S 3c");
      put("5.6",   "4+",     "13",    "14",    "IV+/V-",     "MS 4a");
      put("5.7",   "5a",     "14/15", "15",    "V-/V",       "S/HS 4b");
      put("5.8",   "5b",     "15/16", "16",    "V+/VI-",     "HS/VS 4b");
      put("5.9",   "5c",     "17",    "17/18", "VI-/VI",     "HVS 4c");
      put("5.10a", "6a",     "18",    "19",    "VI/VI+",     "HVS 5a");
      put("5.10b", "6a+",    "19",    "20",    "VII-",       "E1 5a");
      put("5.10c", "6b",     "20",    "21",    "VII-/VII",   "E1 5b");
      put("5.10d", "6b+",    "20/21", "22",    "VII/VII+",   "E2 5b");
      put("5.11a", "6c",     "21",    "22/23", "VII+",       "E2 5c");
      put("5.11b", "6c/6c+", "22",    "23/24", "VIII-",      "E3 5c");
      put("5.11c", "6c+",    "22/23", "24",    "VIII",       "E3 6a");
      put("5.11d", "7a",     "23",    "25",    "VIII/VIII+", "E4 6a");
      put("5.12a", "7a+",    "24",    "26",    "VIII+",      "E4 6b");
      put("5.12b", "7b",     "25",    "27",    "IX-",        "E5 6b");
      put("5.12c", "7b+",    "26",    "28",    "IX-/IX",     "E5/E6 6b");
      put("5.12d", "7c",     "27",    "29",    "IX/IX+",     "E6 6b");
      put("5.13a", "7c+",    "28",    "30",    "IX+",        "E6 6c");
      put("5.13b", "8a",     "29",    "31",    "X-",         "E7 6c");
      put("5.13c", "8a+",    "30",    "32",    "X-/X",       "E7 7a");
      put("5.13d", "8b",     "31",    "33",    "X/X+",       "E8 7a");
      put("5.14a", "8b+",    "32",    "34",    "X+",         "E8 7b");
      put("5.14b", "8c",     "33",    "35",    "XI-",        "E9 7b");
      put("5.14c", "8c+",    "34",    "36",    "XI",         "E10 7b");
      put("5.14d", "9a",     "35",    "37",    "XI+",        "E10 7c");
      put("5.15a", "9a+",    "36",    "38",    "XI+/XII-",   "E11 7c");
      put("5.15b", "9b",     "37",    "39",    "XII-/XII",   "E11 8a");
      put("5.15c", "9b+",    "38",    "40",    "XII",        "E11 8b");
      put("5.15d", "9c",     "39",    "41",    "XII+",       "E11+8c ");
      //@formatter:on
    }

    private void put(String usa, String france, String australia, String southAfrica, String germany, String uk) {
      known.put(rowNumber, Country.USA, usa);
      known.put(rowNumber, Country.FRANCE, france);
      known.put(rowNumber, Country.AUSTRALIA, australia);
      known.put(rowNumber, Country.SOUTH_AFRICA, southAfrica);
      known.put(rowNumber, Country.GERMANY, germany);
      known.put(rowNumber, Country.UNITED_KINGDOM, uk);
      rowNumber++;
    }
  }
}
