package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void nullElseBlock() throws IOException{shouldCompileTo("{{^null}}falsy{{/null}}",$,"falsy");}
}
