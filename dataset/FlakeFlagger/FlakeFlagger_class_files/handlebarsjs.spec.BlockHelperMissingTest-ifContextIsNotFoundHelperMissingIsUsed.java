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

  @Test public void ifContextIsNotFoundHelperMissingIsUsed() throws IOException{String string="{{hello}} {{link_to world}}";String context="{ hello: Hello, world: world }";Hash helpers=$(Handlebars.HELPER_MISSING,new Helper<String>(){@Override public CharSequence apply(final String context,final Options options) throws IOException{return new Handlebars.SafeString("<a>" + context + "</a>");}});shouldCompileTo(string,context,helpers,"Hello <a>world</a>");}
}
