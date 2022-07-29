package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void helperWithComplexLookupExpression() throws IOException{String string="{{#goodbyes}}{{../name}}{{/goodbyes}}";String hash="{name: Alan}";Hash helpers=$("goodbyes",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{String out="";String[] byes={"Goodbye","goodbye","GOODBYE"};for (String bye:byes){out+=bye + " " + options.fn(this) + "! ";}return out;}});shouldCompileTo(string,hash,helpers,"Goodbye Alan! goodbye Alan! GOODBYE Alan! ");}
}
