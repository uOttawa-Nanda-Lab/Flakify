package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.HandlebarsException;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class StringLiteralParametersTest extends AbstractTest {

  @Test public void simpleMultiParamsWork() throws IOException{String string="Message: {{goodbye cruel world}}";String hash="{cruel: cruel, world: world}";Hash helpers=$("goodbye",new Helper<String>(){@Override public CharSequence apply(final String cruel,final Options options) throws IOException{return "Goodbye " + cruel + " " + options.get("world");}});shouldCompileTo(string,hash,helpers,"Message: Goodbye cruel world","regular helpers with multiple params");}

}
