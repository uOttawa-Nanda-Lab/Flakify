@Test public void loadAndRefreshFromRelativeResourceLocation() throws Exception {
  this.context=new XmlEmbeddedWebApplicationContext();
  this.context.load(getClass(),FILE);
  this.context.refresh();
  verifyContext();
}
