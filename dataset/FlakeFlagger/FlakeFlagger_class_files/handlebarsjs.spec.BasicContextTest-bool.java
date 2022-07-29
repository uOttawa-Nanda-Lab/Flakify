package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @Test
  public void bool() throws IOException {
    String string = "{{#goodbye}}GOODBYE {{/goodbye}}cruel {{world}}!";
    shouldCompileTo(string, "{goodbye: true, world: world}", "GOODBYE cruel world!",
        "booleans show the contents when true");

    shouldCompileTo(string, "{goodbye: false, world: world}", "cruel world!",
        "booleans do not show the contents when false");
  }
}
