@Test public void htmlString() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var s = '{{" + selector + "}}';");
  assertEquals("var s = '&lt;div&gt;';",template.apply("<div>"));
}
