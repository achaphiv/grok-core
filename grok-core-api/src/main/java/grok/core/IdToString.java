package grok.core;

import feign.Param;

public class IdToString implements Param.Expander {
  @Override
  public String expand(Object o) {
    if(o instanceof Id) {
      return ((Id) o).value();
    }
    throw new IllegalArgumentException(o + " is not an instance of Id");
  }
}
