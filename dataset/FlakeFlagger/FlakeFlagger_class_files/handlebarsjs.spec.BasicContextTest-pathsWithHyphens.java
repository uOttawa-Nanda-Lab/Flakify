package handlebarsjs.spec;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BasicContextTest extends AbstractTest {


  @Test public void pathsWithHyphens() throws IOException{shouldCompileTo("{{foo-bar}}","{foo-bar: baz}","baz","Paths can contain hyphens (-)");shouldCompileTo("{{foo.foo-bar}}","{foo: {foo-bar: baz}}","baz","Paths can contain hyphens (-)");shouldCompileTo("{{foo/foo-bar}}","{foo: {foo-bar: baz}}","baz","Paths can contain hyphens (-)");}
}
