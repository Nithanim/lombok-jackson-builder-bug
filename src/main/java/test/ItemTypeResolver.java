package test;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class ItemTypeResolver implements TypeIdResolver {
    @Override public void init(JavaType javaType) {

    }

    @Override public String idFromValue(Object o) {
        return null;
    }

    @Override public String idFromValueAndType(Object o, Class<?> aClass) {
        return null;
    }

    @Override public String idFromBaseType() {
        return null;
    }

    @Override public JavaType typeFromId(DatabindContext databindContext, String s) throws IOException {
        return null;
    }

    @Override public String getDescForKnownTypeIds() {
        return null;
    }

    @Override public JsonTypeInfo.Id getMechanism() {
        return null;
    }
}
