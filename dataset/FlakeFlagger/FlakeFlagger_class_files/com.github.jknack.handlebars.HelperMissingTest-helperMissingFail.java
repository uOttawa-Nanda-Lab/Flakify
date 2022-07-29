package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class HelperMissingTest extends AbstractTest {

  /**
 * Handlebars syntax, it MUST fail.
 * @throws IOException
 */@Test(expected=HandlebarsException.class) public void helperMissingFail() throws IOException{shouldCompileTo("{{missing x}}",new Object(),"must fail");}
}
