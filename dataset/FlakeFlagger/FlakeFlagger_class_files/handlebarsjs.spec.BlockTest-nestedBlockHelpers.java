package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void nestedBlockHelpers() throws IOException{String string="{{#form yehuda}}<p>{{name}}</p>{{#link}}Hello{{/link}}{{/form}}";String hash="yehuda: {name: Yehuda}";Hash helpers=$("form",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "<form>" + options.fn(context) + "</form>";}},"link",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "<a href='" + options.get("name") + "'>" + options.fn(this) + "</a>";}});shouldCompileTo(string,hash,helpers,"<form><p>Yehuda</p><a href='Yehuda'>Hello</a></form>","Both blocks executed");}
}
