package grok.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.geojson.GeoJsonObject;

@AutoValue
public abstract class SearchRequest {

  public static SearchRequest of(@JsonProperty("search") String search,
                                 @JsonProperty("location") GeoJsonObject location) {
    return new AutoValue_SearchRequest.Builder_AutoValue_SearchRequest()
            .search(search)
            .location(location)
            .build();
  }

  public abstract String search();
  public abstract GeoJsonObject location();
}
