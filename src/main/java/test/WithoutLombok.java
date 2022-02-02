package test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import test.hierarchy.Parent;

/**
 * In this case, the builder is defined manually (de-lombok) in order to apply {@link
 * JsonTypeIdResolver} to the setter of the builder. Jackson successfully deserialized the object.
 */
@Value
@ToString
@EqualsAndHashCode
@JsonDeserialize(builder = WithoutLombok.WithoutLombokBuilder.class)
public class WithoutLombok {
  String decider;
  Parent choice;

  public static void main(String[] args) throws Exception {
    ObjectMapper om = new ObjectMapper();
    String json = "{\"decider\":\"cB\", \"choice\": {\"stuff\": \"myString\"}}";
    WithoutLombok pojo = om.readValue(json, WithoutLombok.class);
    System.out.println(pojo);
  }

  public static WithoutLombokBuilder builder() {
    return new WithoutLombokBuilder();
  }

  @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
  public static class WithoutLombokBuilder {
    private String decider;
    private Parent choice;

    public WithoutLombokBuilder decider(String decider) {
      this.decider = decider;
      return this;
    }

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.CUSTOM,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "decider",
        visible = true)
    @JsonTypeIdResolver(ChildTypeResolver.class)
    public WithoutLombokBuilder choice(Parent choice) {
      this.choice = choice;
      return this;
    }

    public WithoutLombok build() {
      return new WithoutLombok(decider, choice);
    }

    public String toString() {
      return "WithoutLombok.WithoutLombokBuilder(decider="
          + this.decider
          + ", choice="
          + this.choice
          + ")";
    }
  }
}
