@Test public void toJSON() throws IOException {
  Handlebars handlebars=new Handlebars();
  handlebars.registerHelper("@json",Jackson2Helper.INSTANCE);
  Template template=handlebars.compile("{{@json this}}");
  CharSequence result=template.apply(new Blog("First Post","..."));
  assertEquals("{\"title\":\"First Post\",\"body\":\"...\",\"comments\":[]}",result);
}
