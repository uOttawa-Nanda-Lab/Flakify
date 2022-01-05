@Test public void setBasePathWithDashDash() throws IOException {
  TemplateLoader locator=new ClassPathTemplateLoader("/mustache/specs/",".yml");
  Reader reader=locator.load(URI.create("comments"));
  assertNotNull(reader);
  reader.close();
}
