package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @Test public void nestedPaths() throws IOException{shouldCompileTo("Goodbye {{alan/expression}} world!","{alan: {expression: beautiful}}","Goodbye beautiful world!","Nested paths access nested objects");}
}
