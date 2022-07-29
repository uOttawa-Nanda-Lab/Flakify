package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @SuppressWarnings("unused") @Test public void functionReturningSafeStringsShouldnotBeEscaped() throws IOException{Object hash=new Object(){public Object getAwesome(){return new Handlebars.SafeString("&\"\\<>");}};shouldCompileTo("{{awesome}}",hash,"&\"\\<>","functions returning safestrings aren't escaped");}
}
