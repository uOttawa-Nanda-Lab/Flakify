@Test public void load() throws IOException {
  SpringTemplateLoader loader=new SpringTemplateLoader(new DefaultResourceLoader());
  Reader reader=loader.load(URI.create("template"));
  assertNotNull(reader);
}
