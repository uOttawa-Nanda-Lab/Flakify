@Test public void integer() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var i = {{" + selector + "}};");
  assertEquals("var i = 10;",template.apply(10));
}
