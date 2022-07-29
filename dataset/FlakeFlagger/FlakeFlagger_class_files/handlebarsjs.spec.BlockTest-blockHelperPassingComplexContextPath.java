package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void blockHelperPassingComplexContextPath() throws IOException{String string="{{#form yehuda/cat}}<p>{{name}}</p>{{/form}}";String hash="{yehuda: {name: Yehuda, cat: {name: Harold}}}";Hash helpers=$("form",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "<form>" + options.fn(context) + "</form>";}});shouldCompileTo(string,hash,helpers,"<form><p>Harold</p></form>","Complex path variable resolved");}
}
