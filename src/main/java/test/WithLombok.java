package test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import test.hierarchy.Parent;

/**
 * In this case, the builder is generated by lombok. {@link JsonTypeIdResolver} is not copied to the
 * setter of the builder, thus jackson fails to deserialize the object.
 */
@Value
@Builder
@Jacksonized
public class WithLombok {
  String decider;

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.CUSTOM,
      include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
      property = "decider",
      visible = true)
  @JsonTypeIdResolver(ChildTypeResolver.class)
  Parent choice;

  public static void main(String[] args) throws Exception {
    ObjectMapper om = new ObjectMapper();
    String json = "{\"decider\":\"cB\", \"choice\": {\"stuff\": \"myString\"}}";
    WithLombok pojo = om.readValue(json, WithLombok.class);
    System.out.println(pojo);
  }
}