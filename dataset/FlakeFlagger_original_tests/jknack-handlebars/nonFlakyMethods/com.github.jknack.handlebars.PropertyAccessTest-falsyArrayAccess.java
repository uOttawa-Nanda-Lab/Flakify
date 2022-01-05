@Test public void falsyArrayAccess() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{#if array.[0]}}S1{{/if}}");
  Map<String,Object> context=new HashMap<String,Object>();
  context.put("array",new String[]{""});
  assertEquals("",template.apply(context));
}
