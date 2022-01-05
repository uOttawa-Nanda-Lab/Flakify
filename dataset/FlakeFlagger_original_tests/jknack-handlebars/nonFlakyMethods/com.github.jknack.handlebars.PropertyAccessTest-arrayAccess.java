@Test public void arrayAccess() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{array.[0]}}");
  Map<String,Object> context=new HashMap<String,Object>();
  context.put("array",new String[]{"s1","s2"});
  assertEquals("s1",template.apply(context));
}
