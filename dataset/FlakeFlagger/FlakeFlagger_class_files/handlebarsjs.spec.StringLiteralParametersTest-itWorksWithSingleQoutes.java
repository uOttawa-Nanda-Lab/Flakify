package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.HandlebarsException;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class StringLiteralParametersTest extends AbstractTest {

  @Test public void itWorksWithSingleQoutes() throws IOException{String string="Message: {{{hello \"Alan\'s world\"}}}";Hash helpers=$("hello",new Helper<String>(){@Override public CharSequence apply(final String param,final Options options) throws IOException{return "Hello " + param;}});shouldCompileTo(string,$,helpers,"Message: Hello Alan's world","template with a ' mark");}

}
