@Test public void safeString() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var s = '{{" + selector + "}}';");
  assertEquals("var s = '<div>';",template.apply(new Handlebars.SafeString("<div>")));
}
