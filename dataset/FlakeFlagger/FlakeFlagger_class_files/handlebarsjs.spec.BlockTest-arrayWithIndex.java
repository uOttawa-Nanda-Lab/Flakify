package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void arrayWithIndex() throws IOException{String string="{{#goodbyes}}{{@index}}. {{text}}! {{/goodbyes}}cruel {{world}}!";Object hash=$("goodbyes",new Object[]{$("text","goodbye"),$("text","Goodbye"),$("text","GOODBYE")},"world","world");shouldCompileTo(string,hash,"0. goodbye! 1. Goodbye! 2. GOODBYE! cruel world!","The @index variable is used");}
}
