@Test public void testSuccessfulCreation(){
  factory=new XmlModelFactory(){
    @Override protected InputStream getModelResourceAsStream(){
      return Thread.currentThread().getContextClassLoader().getResourceAsStream("wro1.xml");
    }
  }
;
  final WroModel model=factory.create();
  LOG.debug("model: " + model);
}
