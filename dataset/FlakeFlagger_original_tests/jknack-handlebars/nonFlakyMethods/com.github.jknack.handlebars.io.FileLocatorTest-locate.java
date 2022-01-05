@Test public void locate() throws IOException {
  TemplateLoader locator=new FileTemplateLoader(new File("src/test/resources"));
  Reader reader=locator.load(URI.create("template"));
  assertNotNull(reader);
  reader.close();
}
