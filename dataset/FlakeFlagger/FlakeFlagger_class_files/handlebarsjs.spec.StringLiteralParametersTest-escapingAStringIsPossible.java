package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.HandlebarsException;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class StringLiteralParametersTest extends AbstractTest {

  @Test public void escapingAStringIsPossible() throws IOException{String string="Message: {{{hello \"\\\"world\\\"\"}}}";Hash helpers=$("hello",new Helper<String>(){@Override public CharSequence apply(final String param,final Options options) throws IOException{return "Hello " + param;}});shouldCompileTo(string,$,helpers,"Message: Hello \"world\"","template with an escaped String literal");}

}
