package test;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import test.hierarchy.ChildA;
import test.hierarchy.ChildB;

public class ChildTypeResolver extends TypeIdResolverBase {

  @Override
  public JavaType typeFromId(DatabindContext context, String id) throws IOException {
    if ("cA".equals(id)) {
      return context.getTypeFactory().constructType(ChildA.class);
    } else if ("cB".equals(id)) {
      return context.getTypeFactory().constructType(ChildB.class);
    } else {
      throw new IllegalStateException("Unexpected id " + id + " for type");
    }
  }

  @Override
  public String idFromValue(Object value) {
    return null;
  }

  @Override
  public String idFromValueAndType(Object value, Class<?> suggestedType) {
    return null;
  }

  @Override
  public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CUSTOM;
  }
}
