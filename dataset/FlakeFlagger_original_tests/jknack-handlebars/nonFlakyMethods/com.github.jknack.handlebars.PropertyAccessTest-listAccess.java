@Test public void listAccess() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{list.[1]}}");
  Map<String,Object> context=new HashMap<String,Object>();
  context.put("list",Arrays.asList("s1","s2"));
  assertEquals("s2",template.apply(context));
}
