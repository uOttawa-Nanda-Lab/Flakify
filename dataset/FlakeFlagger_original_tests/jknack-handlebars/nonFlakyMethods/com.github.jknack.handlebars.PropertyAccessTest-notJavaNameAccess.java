@Test public void notJavaNameAccess() throws IOException {
  Handlebars handlebars=new Handlebars();
  Map<String,Object> context=new HashMap<String,Object>();
  context.put("1foo","foo");
  context.put("'foo'","foo");
  context.put("foo or bar","foo");
  context.put("134","134");
  assertEquals("foo",handlebars.compile("{{this.[1foo]}}").apply(context));
  assertEquals("foo",handlebars.compile("{{this.['foo']}}").apply(context));
  assertEquals("foo",handlebars.compile("{{this.[foo or bar]}}").apply(context));
  assertEquals("134",handlebars.compile("{{this.[134]}}").apply(context));
}
