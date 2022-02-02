package test.hierarchy;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ChildB implements Parent {
  String stuff;
}
