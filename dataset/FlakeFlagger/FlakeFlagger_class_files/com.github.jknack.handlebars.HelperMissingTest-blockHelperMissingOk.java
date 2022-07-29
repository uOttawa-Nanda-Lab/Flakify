package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class HelperMissingTest extends AbstractTest {

  /**
 * Mustache fallback.
 * @throws IOException
 */@Test public void blockHelperMissingOk() throws IOException{shouldCompileTo("{{#missing}}This is a mustache fallback{{/missing}}",new Object(),"");}
}
