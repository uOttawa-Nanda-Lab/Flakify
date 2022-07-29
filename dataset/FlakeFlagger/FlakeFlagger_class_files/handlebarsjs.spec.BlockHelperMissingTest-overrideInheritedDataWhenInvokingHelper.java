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

  @Test public void overrideInheritedDataWhenInvokingHelper() throws IOException{String string="{{#hello}}{{world zomg}}{{/hello}}";Hash helpers=$("hello",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return options.fn(Context.newContext($("exclaim","?","zomg","world")).data("adjective","sad"));}},"world",new Helper<Object>(){@Override public CharSequence apply(final Object thing,final Options options) throws IOException{return options.data("adjective") + " " + thing + options.get("exclaim","");}});Template template=compile(string,helpers);String result=template.apply(Context.newContext($("exclaim",true,"zomg","planet")).data("adjective","happy").data("accessData","#win"));assertEquals("Overriden data output by helper","sad world?",result);}
}
