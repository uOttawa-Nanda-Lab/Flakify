@Test public void createFromRelativeResourceLocation() throws Exception {
  this.context=new XmlEmbeddedWebApplicationContext(getClass(),FILE);
  verifyContext();
}
