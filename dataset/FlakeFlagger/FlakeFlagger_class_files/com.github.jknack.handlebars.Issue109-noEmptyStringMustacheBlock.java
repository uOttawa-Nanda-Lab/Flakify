package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void noEmptyStringMustacheBlock() throws IOException{shouldCompileTo("{{#nonempty}}truthy{{/nonempty}}",$("nonempty","xyz"),"truthy");}
}
