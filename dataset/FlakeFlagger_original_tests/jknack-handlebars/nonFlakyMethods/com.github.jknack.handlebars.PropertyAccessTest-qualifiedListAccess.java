@SuppressWarnings("unchecked") @Test public void qualifiedListAccess() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{list.[0].title}}");
  Map<String,Object> blog=new HashMap<String,Object>();
  blog.put("title","First Post!");
  Map<String,Object> context=new HashMap<String,Object>();
  context.put("list",Arrays.asList(blog));
  assertEquals("First Post!",template.apply(context));
}
