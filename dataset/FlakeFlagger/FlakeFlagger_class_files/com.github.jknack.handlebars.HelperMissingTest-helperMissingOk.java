package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class HelperMissingTest extends AbstractTest {

  /**
 * Mustache fallback.
 * @throws IOException
 */@Test public void helperMissingOk() throws IOException{shouldCompileTo("{{missing}}",new Object(),"");}
}
