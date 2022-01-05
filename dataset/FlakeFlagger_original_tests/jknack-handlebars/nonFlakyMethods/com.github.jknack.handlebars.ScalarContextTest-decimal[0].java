@Test public void decimal() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var d = {{" + selector + "}};");
  assertEquals("var d = 1.34;",template.apply(1.34));
}
