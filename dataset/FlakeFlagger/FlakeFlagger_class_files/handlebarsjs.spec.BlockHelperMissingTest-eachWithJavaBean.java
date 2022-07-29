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

  @Test @SuppressWarnings("unused") public void eachWithJavaBean() throws IOException{String string="{{#each goodbyes}}{{@key}}. {{text}}! {{/each}}cruel {{world}}!";Object hash=new Object(){public Object getGoodbyes(){return new Object(){public Object getB1(){return new Object(){public String getText(){return "goodbye";}};}public Object get2(){return new Object(){public String getText(){return "GOODBYE";}};}};}public String getWorld(){return "world";}};try {shouldCompileTo(string,hash,"b1. goodbye! 2. GOODBYE! cruel world!");} catch (Throwable ex){shouldCompileTo(string,hash,"2. GOODBYE! b1. goodbye! cruel world!");}}
}
