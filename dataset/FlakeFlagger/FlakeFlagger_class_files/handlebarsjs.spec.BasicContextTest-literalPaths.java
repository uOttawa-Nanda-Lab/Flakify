package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @Test public void literalPaths() throws IOException{Object hash=$("@alan",$("expression","beautiful"));shouldCompileTo("Goodbye {{[@alan]/expression}} world!",hash,"Goodbye beautiful world!","Literal paths can be used");}
}
