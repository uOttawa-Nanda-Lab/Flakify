@Test public void chHtml() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var s = '{{" + selector + "}}';");
  assertEquals("var s = '&lt;';",template.apply('<'));
}
