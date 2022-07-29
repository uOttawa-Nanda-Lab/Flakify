package handlebarsjs.spec;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;

public class BlockHelperMissingTest extends AbstractTest {

  @Test public void passingInDataWorksWithHelpersInPartials() throws IOException{String string="{{>my_partial}}";Hash partials=$("my_partial","{{hello}}");Hash helpers=$("hello",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return options.data("adjective") + " " + options.get("noun");}});Template template=compile(string,helpers,partials);String result=template.apply(Context.newContext($("noun","cat")).data("adjective","happy"));assertEquals("Data output by helper inside partial","happy cat",result);}
}
