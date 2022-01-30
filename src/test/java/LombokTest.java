import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import test.Details;
import test.ItemInfo;

public class LombokTest {
  @Test
  void test() throws Exception {
    Field field = ItemInfo.class.getDeclaredField("details");
    Assertions.assertThat(field.getAnnotation(JsonTypeInfo.class)).isNotNull();
    Assertions.assertThat(field.getAnnotation(JsonProperty.class)).isNotNull();
    Assertions.assertThat(field.getAnnotation(JsonTypeIdResolver.class)).isNotNull();

    Method method = ItemInfo.ItemInfoBuilder.class.getMethod("details", Details.class);
    Assertions.assertThat(method.getAnnotation(JsonTypeInfo.class))
        .withFailMessage("JsonTypeInfo not on builder")
        .isNotNull();
    Assertions.assertThat(method.getAnnotation(JsonProperty.class))
        .withFailMessage("JsonProperty not on builder")
        .isNotNull();
    Assertions.assertThat(method.getAnnotation(JsonTypeIdResolver.class))
        .withFailMessage("JsonTypeIdResolver not on builder")
        .isNotNull();
  }
}
