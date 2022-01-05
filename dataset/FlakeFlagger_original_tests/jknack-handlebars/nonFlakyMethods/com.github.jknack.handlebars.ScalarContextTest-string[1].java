@Test public void string() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var s = '{{" + selector + "}}';");
  assertEquals("var s = 'Hello';",template.apply("Hello"));
}
