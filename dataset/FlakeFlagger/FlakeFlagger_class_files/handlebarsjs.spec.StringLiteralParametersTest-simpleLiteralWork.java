package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.HandlebarsException;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class StringLiteralParametersTest extends AbstractTest {

  @Test public void simpleLiteralWork() throws IOException{String string="Message: {{hello \"world\" 12 true false}}";Hash helpers=$("hello",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "Hello " + context + " " + options.param(0) + " times: " + options.param(1) + " " + options.param(2);}});shouldCompileTo(string,$,helpers,"Message: Hello world 12 times: true false","template with a simple String literal");}

}
