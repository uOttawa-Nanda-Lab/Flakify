package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class Issue109 extends AbstractTest {

  @Test public void noEmptyStringIfBlock() throws IOException{shouldCompileTo("{{#if nonempty}}truthy{{/if}}",$("nonempty","xyz"),"truthy");}
}
