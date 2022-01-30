package test;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import jdk.jfr.MemoryAddress;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ItemInfo {
  String name;
  String icon;
  String description;
  String chatLink;
  @Builder.Default int level = -1;
  @Builder.Default int vendorValue = -1;
  @Builder.Default int defaultSkin = -1;
  Set<String> flags;
  String[] restrictions;

  @JsonProperty("myName")
  @JsonTypeInfo(
      use = JsonTypeInfo.Id.CUSTOM,
      include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
      property = "type",
      visible = true)
  @JsonTypeIdResolver(ItemTypeResolver.class)
  @Deprecated
  @JsonAlias("dsfsdf")
  @MemoryAddress
  Details details;

  @SuppressWarnings("unchecked")
  public <T extends Details> T getDetails() {
    return (T) details;
  }
}
