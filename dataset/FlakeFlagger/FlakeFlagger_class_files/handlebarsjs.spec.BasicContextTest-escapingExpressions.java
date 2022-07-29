package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @Test public void escapingExpressions() throws IOException{shouldCompileTo("{{{awesome}}}","{awesome: '&\"\\<>'}","&\"\\<>","expressions with 3 handlebars aren't escaped");shouldCompileTo("{{&awesome}}","{awesome: '&\"\\<>'}","&\"\\<>","expressions with {{& handlebars aren't escaped");shouldCompileTo("{{awesome}}",$("awesome","&\"'`\\<>"),"&amp;&quot;&#x27;&#x60;\\&lt;&gt;","by default expressions should be escaped");shouldCompileTo("{{awesome}}","{awesome: 'Escaped, <b> looks like: &lt;b&gt;'}","Escaped, &lt;b&gt; looks like: &amp;lt;b&amp;gt;","escaping should properly handle amperstands");}
}
