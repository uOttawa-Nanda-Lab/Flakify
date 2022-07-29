package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void blockInvertedSectionsWithEmptyArrays() throws IOException{shouldCompileTo("{{#people}}{{name}}{{^}}{{none}}{{/people}}",$("none","No people","people",new Object[0]),"No people");}
}
