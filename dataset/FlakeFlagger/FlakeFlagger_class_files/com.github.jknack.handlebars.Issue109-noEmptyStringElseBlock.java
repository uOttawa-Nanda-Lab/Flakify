package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void noEmptyStringElseBlock() throws IOException{shouldCompileTo("{{#nonempty}}falsy{{/nonempty}}",$("nonempty","xyz"),"falsy");}
}
