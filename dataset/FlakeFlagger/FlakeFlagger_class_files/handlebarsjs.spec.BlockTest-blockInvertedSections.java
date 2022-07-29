package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void blockInvertedSections() throws IOException{shouldCompileTo("{{#people}}{{name}}{{^}}{{none}}{{/people}}","{none: No people}","No people");}
}
