package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class ScopeTest extends AbstractTest {

  @Test public void currentScope() throws IOException{Object context=$("value","parent","child",$);shouldCompileTo("{{#child}}{{this.value}}{{/child}}",context,"");shouldCompileTo("{{#child}}{{value}}{{/child}}",context,"parent");}
}
