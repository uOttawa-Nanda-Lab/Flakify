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

  @Test public void blockHelperCanTakeOptionalHashWithSingleQuotedStrings() throws IOException{Hash helpers=$("goodbye",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "GOODBYE " + options.hash("cruel") + " " + options.fn(context) + " " + options.hash("times") + " TIMES";}});shouldCompileTo("{{#goodbye cruel='CRUEL' times=12}}world{{/goodbye}}",$,helpers,"GOODBYE CRUEL world 12 TIMES");}
}
