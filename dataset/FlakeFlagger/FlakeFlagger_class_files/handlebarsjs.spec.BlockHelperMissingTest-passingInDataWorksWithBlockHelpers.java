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

  @Test public void passingInDataWorksWithBlockHelpers() throws IOException{String string="{{#hello}}{{world}}{{/hello}}";Hash helpers=$("hello",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return options.fn();}},"world",new Helper<Object>(){@Override public CharSequence apply(final Object thing,final Options options) throws IOException{Boolean exclaim=options.get("exclaim");return options.data("adjective") + " world" + (exclaim?"!":"");}});Template template=compile(string,helpers);String result=template.apply(Context.newContext($("exclaim",true)).data("adjective","happy"));assertEquals("happy world!",result);}
}
