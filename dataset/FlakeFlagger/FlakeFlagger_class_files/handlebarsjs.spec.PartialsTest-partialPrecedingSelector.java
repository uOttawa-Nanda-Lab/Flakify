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

  @Test public void partialPrecedingSelector() throws IOException{String string="Dudes: {{>dude}} {{another_dude}}";String dude="{{name}}";Object hash=$("name","Jeepers","another_dude","Creepers");shouldCompileToWithPartials(string,hash,$("dude",dude),"Dudes: Jeepers Creepers","Regular selectors can follow a partial");}
}
