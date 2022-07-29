package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @Test public void thisKeywordInPaths() throws IOException{String string="{{#goodbyes}}{{this}}{{/goodbyes}}";Object hash=$("goodbyes",new String[]{"goodbye","Goodbye","GOODBYE"});shouldCompileTo(string,hash,"goodbyeGoodbyeGOODBYE","This keyword in paths evaluates to current context");string="{{#hellos}}{{this/text}}{{/hellos}}";hash=$("hellos",new Object[]{$("text","hello"),$("text","Hello"),$("text","HELLO")});shouldCompileTo(string,hash,"helloHelloHELLO","This keyword evaluates in more complex paths");}
}
