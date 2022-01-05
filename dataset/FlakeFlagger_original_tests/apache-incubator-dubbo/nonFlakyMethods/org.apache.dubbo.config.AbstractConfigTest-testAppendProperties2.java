@Test public void testAppendProperties2() throws Exception {
  try {
    System.setProperty("dubbo.properties.two.i","2");
    PropertiesConfig config=new PropertiesConfig("two");
    AbstractConfig.appendProperties(config);
    TestCase.assertEquals(2,config.getI());
  }
  finally {
    System.clearProperty("dubbo.properties.two.i");
  }
}
