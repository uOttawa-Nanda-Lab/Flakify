@Test public void inheritance() throws IOException {
  try {
    Template template=handlebars.compile(URI.create(name));
    CharSequence result=template.apply(new Object());
    String expected=FileUtils.readFileToString(new File("src/test/resources/inheritance/" + name + ".expected"));
    assertEquals(expected,result);
  }
 catch (  HandlebarsException ex) {
    Handlebars.error(ex.getMessage());
    throw ex;
  }
}
