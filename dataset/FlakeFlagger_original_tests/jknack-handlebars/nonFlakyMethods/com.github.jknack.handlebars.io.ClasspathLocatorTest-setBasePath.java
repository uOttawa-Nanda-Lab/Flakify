@Test public void setBasePath() throws IOException {
  TemplateLoader locator=new ClassPathTemplateLoader("/mustache/specs",".yml");
  Reader reader=locator.load(URI.create("comments"));
  assertNotNull(reader);
  reader.close();
}
