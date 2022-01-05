@Test public void defaultModelFactoryIsXml(){
  new BaseWroManagerFactory(){
    @Override protected WroModelFactory newModelFactory(){
      final WroModelFactory modelFactory=super.newModelFactory();
      Assert.assertEquals(XmlModelFactory.class,modelFactory.getClass());
      return modelFactory;
    }
  }
;
}
