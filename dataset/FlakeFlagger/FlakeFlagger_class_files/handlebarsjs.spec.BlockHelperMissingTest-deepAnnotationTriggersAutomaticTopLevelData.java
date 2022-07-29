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

  @Test public void deepAnnotationTriggersAutomaticTopLevelData() throws IOException{String string="{{#let world=\"world\"}}{{#if foo}}{{#if foo}}Hello {{@world}}{{/if}}{{/if}}{{/let}}";Hash helpers=$("let",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{for (Entry<String, Object> entry:options.hash.entrySet()){options.data(entry.getKey(),entry.getValue());}return options.fn(context);}});Template template=compile(string,helpers);String result=template.apply($("foo",true));assertEquals("Hello world",result);}
}
