package com.github.jknack.handlebars;

import java.io.IOException;
import java.util.Locale;

import org.junit.Test;

public class I18NHelperTest extends AbstractTest {

  @Test public void customLocale() throws IOException{shouldCompileTo("{{i18n \"hello\" locale=\"es_AR\"}} Handlebars.java!",$,"Hola Handlebars.java!");}
}
