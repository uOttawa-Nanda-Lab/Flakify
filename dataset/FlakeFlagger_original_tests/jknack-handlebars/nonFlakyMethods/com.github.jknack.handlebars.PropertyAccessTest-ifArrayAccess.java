@Test public void ifArrayAccess() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{#if array.[0]}}S1{{/if}}");
  Map<String,Object> context=new HashMap<String,Object>();
  context.put("array",new String[]{"s1","s2"});
  assertEquals("S1",template.apply(context));
}
