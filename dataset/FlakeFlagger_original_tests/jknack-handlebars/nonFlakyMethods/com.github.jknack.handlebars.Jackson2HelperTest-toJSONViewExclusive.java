@Test public void toJSONViewExclusive() throws IOException {
  Handlebars handlebars=new Handlebars();
  ObjectMapper mapper=new ObjectMapper();
  mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION,false);
  handlebars.registerHelper("@json",new Jackson2Helper(mapper));
  Template template=handlebars.compile("{{@json this view=\"com.github.jknack.handlebars.Blog$Views$Public\"}}");
  CharSequence result=template.apply(new Blog("First Post","..."));
  assertEquals("{\"title\":\"First Post\"}",result);
}
