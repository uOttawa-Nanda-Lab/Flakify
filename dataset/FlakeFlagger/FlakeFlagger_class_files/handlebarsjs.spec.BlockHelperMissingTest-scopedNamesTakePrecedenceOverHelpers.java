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

  @Test public void scopedNamesTakePrecedenceOverHelpers() throws IOException{Hash helpers=$("goodbye",new Helper<Map<String, Object>>(){@Override public CharSequence apply(final Map<String, Object> context,final Options options) throws IOException{return context.get("goodbye").toString().toUpperCase();}},"cruel",new Helper<String>(){@Override public CharSequence apply(final String world,final Options options) throws IOException{return "cruel " + world.toUpperCase();}});shouldCompileTo("{{this.goodbye}} {{cruel world}} {{cruel this.goodbye}}","{goodbye: goodbye, world: world}",helpers,"goodbye cruel WORLD cruel GOODBYE");}
}
