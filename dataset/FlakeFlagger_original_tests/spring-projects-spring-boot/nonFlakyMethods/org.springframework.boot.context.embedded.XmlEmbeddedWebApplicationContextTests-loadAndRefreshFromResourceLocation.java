@Test public void loadAndRefreshFromResourceLocation() throws Exception {
  this.context=new XmlEmbeddedWebApplicationContext();
  this.context.load(PATH + FILE);
  this.context.refresh();
  verifyContext();
}
