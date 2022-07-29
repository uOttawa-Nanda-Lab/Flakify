package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @Test @SuppressWarnings("unused") public void functions() throws IOException{shouldCompileTo("{{awesome}}",new Object(){public Object getAwesome(){return "Awesome";}},"Awesome","functions are called and render their output");shouldCompileTo("{{awesome}}",new Object(){String more="More awesome";public Object getAwesome(){return more;}},"More awesome","functions are called and render their output");}
}
