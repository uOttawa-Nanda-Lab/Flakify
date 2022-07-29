package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class HelperMissingTest extends AbstractTest {

  @Test public void blockHelperMissingOverride() throws IOException{Hash helpers=$(Handlebars.HELPER_MISSING,new Helper<Object>(){@Override public CharSequence apply(final Object context,final Options options) throws IOException{return options.fn.text();}});shouldCompileTo("{{#missing x}}Raw display{{/missing}}",new Object(),helpers,"Raw display");}
}
