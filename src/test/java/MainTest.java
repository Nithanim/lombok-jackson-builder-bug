import org.junit.jupiter.api.Test;

import test.WithLombok;
import test.WithoutLombok;

public class MainTest {
  @Test
  void testWithLombok() throws Exception {
    WithLombok.main(new String[0]);
  }

  @Test
  void testWithoutLombok() throws Exception {
    WithoutLombok.main(new String[0]);
  }
}
