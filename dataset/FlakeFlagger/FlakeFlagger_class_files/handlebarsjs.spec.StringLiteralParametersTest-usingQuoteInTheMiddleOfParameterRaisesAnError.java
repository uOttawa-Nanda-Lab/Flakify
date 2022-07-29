package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.HandlebarsException;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class StringLiteralParametersTest extends AbstractTest {

  @Test(expected=HandlebarsException.class) public void usingQuoteInTheMiddleOfParameterRaisesAnError() throws IOException{shouldCompileTo("Message: {{hello wo\"rld\"}}",$,null);}

}
