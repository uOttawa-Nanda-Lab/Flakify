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

  @Test public void parameterCanBeLookupViaAnnotation() throws IOException{Hash helpers=$("hello",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "Hello " + options.hash("noun");}});Template template=compile("{{hello noun=@world}}",helpers);String result=template.apply(Context.newContext($).data("world","world"));assertEquals("Hello world",result);}
}
