package com.github.jknack.handlebars;

import java.io.IOException;
import java.util.Locale;

import org.junit.Test;

public class I18NHelperTest extends AbstractTest {

  @Test(expected=HandlebarsException.class) public void missingKeyError() throws IOException{shouldCompileTo("{{i18n \"missing\"}}",null,"error");}
}
