package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void blockWithComplexLookup() throws IOException{String string="{{#goodbyes}}{{text}} cruel {{../name}}! {{/goodbyes}}";Object hash=$("goodbyes",new Object[]{$("text","goodbye"),$("text","Goodbye"),$("text","GOODBYE")},"name","Alan");shouldCompileTo(string,hash,"goodbye cruel Alan! Goodbye cruel Alan! GOODBYE cruel Alan! ","Templates can access variables in contexts up the stack with relative path syntax");}
}
