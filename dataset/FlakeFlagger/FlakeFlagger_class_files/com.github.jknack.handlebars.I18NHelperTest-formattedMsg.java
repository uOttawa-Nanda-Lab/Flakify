package com.github.jknack.handlebars;

import java.io.IOException;
import java.util.Locale;

import org.junit.Test;

public class I18NHelperTest extends AbstractTest {

  @Test public void formattedMsg() throws IOException{shouldCompileTo("{{i18n \"formatted\" \"Handlebars.java\"}}!",null,"Hi Handlebars.java!");}
}
