package com.github.jknack.handlebars;

import java.io.IOException;

import org.junit.Test;

public class HelperMissingTest extends AbstractTest {

  @Test(expected=HandlebarsException.class) public void blockHelperMissingFail() throws IOException{shouldCompileTo("{{#missing x}}This is a mustache fallback{{/missing}}",new Object(),"must fail");}
}
