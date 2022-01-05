@Test public void toJSONAliasViewExclusive() throws IOException {
  Handlebars handlebars=new Handlebars();
  ObjectMapper mapper=new ObjectMapper();
  mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION,false);
  handlebars.registerHelper("@json",new Jackson2Helper(mapper).viewAlias("myView",Public.class));
  Template template=handlebars.compile("{{@json this view=\"myView\"}}");
  CharSequence result=template.apply(new Blog("First Post","..."));
  assertEquals("{\"title\":\"First Post\"}",result);
}
