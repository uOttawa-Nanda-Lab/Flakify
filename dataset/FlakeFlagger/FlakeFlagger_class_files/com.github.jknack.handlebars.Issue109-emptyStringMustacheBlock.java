package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void emptyStringMustacheBlock() throws IOException{shouldCompileTo("{{#empty}}truthy{{/empty}}",$("empty",""),"");}
}
