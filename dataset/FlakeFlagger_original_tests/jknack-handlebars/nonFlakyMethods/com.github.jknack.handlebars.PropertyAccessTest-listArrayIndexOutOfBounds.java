@Test public void listArrayIndexOutOfBounds() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{list.[10]}}");
  Map<String,Object> context=new HashMap<String,Object>();
  context.put("list",Arrays.asList("s1","s2"));
  try {
    assertEquals("s2",template.apply(context));
    fail("An " + ArrayIndexOutOfBoundsException.class.getName() + " is expected.");
  }
 catch (  HandlebarsException ex) {
    assertTrue(ex.getCause() instanceof ArrayIndexOutOfBoundsException);
  }
}
