@Test public void array() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{#" + selector + "}}{{"+ selector+ "}} {{/"+ selector+ "}}");
  assertEquals("1 2 3 ",template.apply(new Object[]{1,2,3}));
}
