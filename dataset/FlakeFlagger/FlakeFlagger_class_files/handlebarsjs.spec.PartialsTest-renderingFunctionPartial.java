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

  @Test public void renderingFunctionPartial() throws IOException{String string="Dudes: {{#dudes}}{{> dude}}{{/dudes}}";String partial="{{name}} ({{url}}) ";Object hash=$("dudes",new Object[]{$("name","Yehuda","url","http://yehuda"),$("name","Alan","url","http://alan")});shouldCompileToWithPartials(string,hash,$("dude",partial),"Dudes: Yehuda (http://yehuda) Alan (http://alan) ","Function partials output based in VM.");}
}
