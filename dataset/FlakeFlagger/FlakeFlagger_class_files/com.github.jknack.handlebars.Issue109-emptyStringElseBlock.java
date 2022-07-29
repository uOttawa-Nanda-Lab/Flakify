package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void emptyStringElseBlock() throws IOException{shouldCompileTo("{{^empty}}falsy{{/empty}}",$("empty",""),"falsy");}
}
