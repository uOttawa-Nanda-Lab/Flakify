@Test public void createFromResource() throws Exception {
  this.context=new XmlEmbeddedWebApplicationContext(new ClassPathResource(FILE,getClass()));
  verifyContext();
}
