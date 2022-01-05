@Test public void ch() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var s = '{{" + selector + "}}';");
  assertEquals("var s = 'c';",template.apply('c'));
}
