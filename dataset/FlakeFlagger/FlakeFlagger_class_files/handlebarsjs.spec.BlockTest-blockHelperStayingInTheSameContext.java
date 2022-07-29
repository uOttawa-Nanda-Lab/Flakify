package handlebarsjs.spec;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

public class BlockTest extends AbstractTest {

  @Test public void blockHelperStayingInTheSameContext() throws IOException{String string="{{#form}}<p>{{name}}</p>{{/form}}";String hash="{name: Yehuda}";Hash helpers=$("form",new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return "<form>" + options.fn(this) + "</form>";}});shouldCompileTo(string,hash,helpers,"<form><p>Yehuda</p></form>","Block helper executed with current context");}
}
