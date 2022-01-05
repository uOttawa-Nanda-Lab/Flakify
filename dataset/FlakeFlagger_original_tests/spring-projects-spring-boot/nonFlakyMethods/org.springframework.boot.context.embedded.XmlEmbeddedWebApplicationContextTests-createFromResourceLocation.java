@Test public void createFromResourceLocation() throws Exception {
  this.context=new XmlEmbeddedWebApplicationContext(PATH + FILE);
  verifyContext();
}
