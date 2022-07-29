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

  @Test public void partialInPartial() throws IOException{String string="Dudes: {{#dudes}}{{>dude}}{{/dudes}}";String dude="{{name}} {{> url}} ";String url="<a href='{{url}}'>{{url}}</a>";Object hash=$("dudes",new Object[]{$("name","Yehuda","url","http://yehuda"),$("name","Alan","url","http://alan")});shouldCompileToWithPartials(string,hash,$("dude",dude,"url",url),"Dudes: Yehuda <a href='http://yehuda'>http://yehuda</a> Alan <a href='http://alan'>http://alan</a> ","Partials are rendered inside of other partials");}
}
