@Test public void locate() throws IOException {
  TemplateLoader locator=new ClassPathTemplateLoader();
  Reader reader=locator.load(URI.create("template"));
  assertNotNull(reader);
  reader.close();
}
