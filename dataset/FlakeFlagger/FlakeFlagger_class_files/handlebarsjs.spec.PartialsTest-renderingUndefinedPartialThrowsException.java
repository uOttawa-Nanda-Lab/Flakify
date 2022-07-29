package handlebarsjs.spec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.HandlebarsError;
import com.github.jknack.handlebars.HandlebarsException;

public class PartialsTest extends AbstractTest {

  @Test public void renderingUndefinedPartialThrowsException() throws IOException{try {shouldCompileTo("{{> whatever}}",$,null);fail("rendering undefined partial throws an exception");} catch (HandlebarsException ex){HandlebarsError error=ex.getError();assertNotNull(error);assertEquals("The partial '/whatever.hbs' could not be found",error.reason);}}
}
