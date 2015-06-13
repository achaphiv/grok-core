package grok.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.mongojack.Id;

import javax.annotation.Nullable;
import java.util.Date;

@AutoValue
public abstract class CragUpdate {

  @JsonCreator
  public static CragUpdate of(
      @Id @JsonProperty("id") String id,
      @JsonProperty("client") String clientId,
      @JsonProperty("date") Date date,
      @JsonProperty("crag") Crag crag) {
    return new AutoValue_CragUpdate(id, clientId, date, crag);
  }

  @Id
  @Nullable
  @JsonProperty
  public abstract String id();

  @JsonProperty
  public abstract String clientId();

  @JsonProperty
  public abstract Date date();

  @JsonProperty
  public abstract Crag crag();
}
