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

  @Test public void argumentsToHelpersCanBeRetrievedFromOptionsHashInStringForm() throws IOException{Hash helpers=$("wycats",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "HELP ME MY BOSS " + options.param(0) + ' ' + options.param(1);}});assertEquals("HELP ME MY BOSS is.a slave.driver",compile("{{wycats this is.a slave.driver}}",helpers,true).apply($));}
}
