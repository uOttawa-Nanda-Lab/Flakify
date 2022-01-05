@Test public void bool() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("if ({{" + selector + "}})");
  assertEquals("if (true)",template.apply(true));
}
