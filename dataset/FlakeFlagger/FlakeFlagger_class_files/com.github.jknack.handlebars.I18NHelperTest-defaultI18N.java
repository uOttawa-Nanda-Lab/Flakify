package com.github.jknack.handlebars;

import java.io.IOException;
import java.util.Locale;

import org.junit.Test;

public class I18NHelperTest extends AbstractTest {

  @Test public void defaultI18N() throws IOException{shouldCompileTo("{{i18n \"hello\"}} Handlebars.java!",$,"Hi Handlebars.java!");}
}
