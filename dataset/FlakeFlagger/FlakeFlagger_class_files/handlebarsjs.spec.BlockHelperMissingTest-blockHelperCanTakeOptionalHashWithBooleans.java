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

  @Test public void blockHelperCanTakeOptionalHashWithBooleans() throws IOException{Hash helpers=$("goodbye",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{Boolean print=options.hash("print");if (print){return "GOODBYE " + options.hash("cruel") + " " + options.fn(context);} else {return "NOT PRINTING";}}});shouldCompileTo("{{#goodbye cruel=\"CRUEL\" print=true}}world{{/goodbye}}",$,helpers,"GOODBYE CRUEL world");shouldCompileTo("{{#goodbye cruel=\"CRUEL\" print=false}}world{{/goodbye}}",$,helpers,"NOT PRINTING");}
}
