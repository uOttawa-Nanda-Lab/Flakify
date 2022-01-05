@Test public void dontEscape() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var s = '{{{" + selector + "}}}';");
  assertEquals("var s = '<div>';",template.apply("<div>"));
}
