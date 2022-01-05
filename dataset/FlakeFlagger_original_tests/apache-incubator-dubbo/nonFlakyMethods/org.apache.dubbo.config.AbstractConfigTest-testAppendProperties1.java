@Test public void testAppendProperties1() throws Exception {
  try {
    System.setProperty("dubbo.properties.i","1");
    System.setProperty("dubbo.properties.c","c");
    System.setProperty("dubbo.properties.b","2");
    System.setProperty("dubbo.properties.d","3");
    System.setProperty("dubbo.properties.f","4");
    System.setProperty("dubbo.properties.l","5");
    System.setProperty("dubbo.properties.s","6");
    System.setProperty("dubbo.properties.str","dubbo");
    System.setProperty("dubbo.properties.bool","true");
    PropertiesConfig config=new PropertiesConfig();
    AbstractConfig.appendProperties(config);
    TestCase.assertEquals(1,config.getI());
    TestCase.assertEquals('c',config.getC());
    TestCase.assertEquals((byte)0x02,config.getB());
    TestCase.assertEquals(3d,config.getD());
    TestCase.assertEquals(4f,config.getF());
    TestCase.assertEquals(5L,config.getL());
    TestCase.assertEquals(6,config.getS());
    TestCase.assertEquals("dubbo",config.getStr());
    TestCase.assertTrue(config.isBool());
  }
  finally {
    System.clearProperty("dubbo.properties.i");
    System.clearProperty("dubbo.properties.c");
    System.clearProperty("dubbo.properties.b");
    System.clearProperty("dubbo.properties.d");
    System.clearProperty("dubbo.properties.f");
    System.clearProperty("dubbo.properties.l");
    System.clearProperty("dubbo.properties.s");
    System.clearProperty("dubbo.properties.str");
    System.clearProperty("dubbo.properties.bool");
  }
}
