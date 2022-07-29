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

  @Test public void helperCanTakeOptionalHashWithBooleans() throws IOException{Hash helpers=$("goodbye",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{Boolean print=options.hash("print");if (print){return "GOODBYE " + options.hash("cruel") + " " + options.hash("world");} else {return "NOT PRINTING";}}});shouldCompileTo("{{goodbye cruel=\"CRUEL\" world=\"WORLD\" print=true}}",$,helpers,"GOODBYE CRUEL WORLD");shouldCompileTo("{{goodbye cruel=\"CRUEL\" world=\"WORLD\" print=false}}",$,helpers,"NOT PRINTING");}
}
