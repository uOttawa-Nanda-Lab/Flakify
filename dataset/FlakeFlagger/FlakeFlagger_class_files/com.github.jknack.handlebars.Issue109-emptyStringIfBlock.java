package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void emptyStringIfBlock() throws IOException{shouldCompileTo("{{#if empty}}truthy{{else}}falsy{{/if}}",$("empty",""),"falsy");}
}
