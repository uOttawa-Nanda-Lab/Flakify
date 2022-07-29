package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class SwitchPartialContextTest extends AbstractTest {

  @Test public void partialWithContext() throws IOException{String partial="{{#this}}{{name}} {{/this}}";Hash hash=$("dudes",new Object[]{$("name","moe"),$("name","curly")});shouldCompileToWithPartials("Dudes: {{>dude dudes}}",hash,$("dude",partial),"Dudes: moe curly ");}
}
