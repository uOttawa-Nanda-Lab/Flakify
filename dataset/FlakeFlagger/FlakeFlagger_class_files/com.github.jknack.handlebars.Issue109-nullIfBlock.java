package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void nullIfBlock() throws IOException{shouldCompileTo("{{#if null}}truthy{{else}}falsy{{/if}}",$,"falsy");}
}
