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

  @Test public void whenInsideABlockInStringModePassesTheAppropriateContextInTheOptionsHash() throws IOException{Hash helpers=$("tomdale",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "STOP ME FROM READING HACKER NEWS I " + context + " " + options.param(0);}},"with",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return options.fn(context);}});assertEquals("STOP ME FROM READING HACKER NEWS I need-a dad.joke",compile("{{#with dale}}{{tomdale ../need dad.joke}}{{/with}}",helpers,true).apply($("dale",$,"need","need-a")));}
}
