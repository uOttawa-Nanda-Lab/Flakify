@Test public void defaultI18nJs() throws IOException {
  String defaultLocale=Locale.getDefault().toString();
  String expected="<script type='text/javascript'>\n" + "  I18n.defaultLocale = '" + defaultLocale + "';\n"+ "  I18n.locale = '"+ defaultLocale+ "';\n"+ "  I18n.translations = I18n.translations || {};\n"+ "  // Spanish (Argentina)\n"+ "  I18n.translations['es_AR'] = {\n"+ "    \"hello\": \"Hola\",\n"+ "    \"formatted\": \"Hi {{arg0}}\"\n"+ "  };\n"+ "</script>\n";
  shouldCompileTo("{{i18nJs \"es_AR\"}}",null,expected);
}
