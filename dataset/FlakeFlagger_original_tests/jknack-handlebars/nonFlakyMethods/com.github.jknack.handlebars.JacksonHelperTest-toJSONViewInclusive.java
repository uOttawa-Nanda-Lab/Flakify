@Test public void toJSONViewInclusive() throws IOException {
  Handlebars handlebars=new Handlebars();
  handlebars.registerHelper("@json",JacksonHelper.INSTANCE);
  Template template=handlebars.compile("{{@json this view=\"com.github.jknack.handlebars.Blog$Views$Public\"}}");
  CharSequence result=template.apply(new Blog("First Post","..."));
  assertEquals("{\"title\":\"First Post\",\"body\":\"...\",\"comments\":[]}",result);
}
