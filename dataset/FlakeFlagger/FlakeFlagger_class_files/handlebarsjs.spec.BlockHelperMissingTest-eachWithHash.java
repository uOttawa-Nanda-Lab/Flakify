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

  @Test public void eachWithHash() throws IOException{String string="{{#each goodbyes}}{{@key}}. {{text}}! {{/each}}cruel {{world}}!";Object hash=$("goodbyes",$("<b>#1</b>",$("text","goodbye"),"2",$("text","GOODBYE")),"world","world");shouldCompileTo(string,hash,"&lt;b&gt;#1&lt;/b&gt;. goodbye! 2. GOODBYE! cruel world!");}
}
