package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void blockWithDeepNestedComplexLookup() throws IOException{String string="{{#outer}}Goodbye {{#inner}}cruel {{../../omg}}{{/inner}}{{/outer}}";Object hash=$("omg","OMG!","outer",new Object[]{$("inner",new Object[]{$("text","goodbye")})});shouldCompileTo(string,hash,"Goodbye cruel OMG!");}
}
