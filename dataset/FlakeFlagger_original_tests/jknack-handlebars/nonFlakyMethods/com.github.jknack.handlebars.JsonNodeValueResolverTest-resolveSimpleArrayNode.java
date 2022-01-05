@Test public void resolveSimpleArrayNode() throws IOException {
  Handlebars handlebars=new Handlebars();
  Map<String,Object> root=new HashMap<String,Object>();
  root.put("array",new Object[]{1,2,3});
  assertEquals("123",handlebars.compile("{{array.[0]}}{{array.[1]}}{{array.[2]}}").apply(context(root)));
  assertEquals("123",handlebars.compile("{{#array}}{{this}}{{/array}}").apply(context(root)));
}
