@Test public void setBasePath() throws IOException {
  TemplateLoader locator=new FileTemplateLoader(new File("src/test/resources/mustache/specs"),".yml");
  Reader reader=locator.load(URI.create("comments"));
  assertNotNull(reader);
  reader.close();
}
