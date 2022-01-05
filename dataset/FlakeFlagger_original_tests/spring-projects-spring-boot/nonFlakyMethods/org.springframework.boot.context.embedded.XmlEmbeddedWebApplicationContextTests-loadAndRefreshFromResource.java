@Test public void loadAndRefreshFromResource() throws Exception {
  this.context=new XmlEmbeddedWebApplicationContext();
  this.context.load(new ClassPathResource(FILE,getClass()));
  this.context.refresh();
  verifyContext();
}
