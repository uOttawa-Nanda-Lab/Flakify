@Test public void subFolder() throws IOException {
  TemplateLoader locator=new FileTemplateLoader(new File("src/test/resources"),".yml");
  Reader reader=locator.load(URI.create("mustache/specs/comments"));
  assertNotNull(reader);
  reader.close();
}
