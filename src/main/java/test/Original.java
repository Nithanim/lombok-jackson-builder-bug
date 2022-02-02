package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Original {
  @JsonProperty("myName")
  @JsonTypeInfo(
      use = JsonTypeInfo.Id.CUSTOM,
      include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
      property = "type",
      visible = true)
  @JsonTypeIdResolver(ItemTypeResolver.class)
  @JsonAlias("test")
  String details;

  public static void main(String[] args) throws Exception {
    Field field = Original.class.getDeclaredField("details");
    Objects.requireNonNull(field.getAnnotation(JsonTypeInfo.class));
    Objects.requireNonNull(field.getAnnotation(JsonProperty.class));
    Objects.requireNonNull(field.getAnnotation(JsonTypeIdResolver.class));

    Method method = Original.OriginalBuilder.class.getMethod("details", String.class);
    Objects.requireNonNull(method.getAnnotation(JsonTypeInfo.class), "JsonTypeInfo not on builder");
    Objects.requireNonNull(method.getAnnotation(JsonProperty.class), "JsonProperty not on builder");

    // This fails
    Objects.requireNonNull(
        method.getAnnotation(JsonTypeIdResolver.class), "JsonTypeIdResolver not on builder");
  }

  public static class ItemTypeResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(Object o) {
      return null;
    }

    @Override
    public String idFromValueAndType(Object o, Class<?> aClass) {
      return null;
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
      return null;
    }
  }
}
