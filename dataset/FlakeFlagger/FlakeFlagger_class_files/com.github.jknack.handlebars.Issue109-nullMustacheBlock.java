package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void nullMustacheBlock() throws IOException{shouldCompileTo("{{#null}}truthy{{/null}}",$,"");}
}
