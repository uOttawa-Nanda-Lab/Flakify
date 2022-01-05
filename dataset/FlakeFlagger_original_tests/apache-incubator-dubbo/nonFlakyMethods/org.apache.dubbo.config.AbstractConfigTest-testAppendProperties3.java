@Test public void testAppendProperties3() throws Exception {
  try {
    Properties p=new Properties();
    p.put("dubbo.properties.str","dubbo");
    ConfigUtils.setProperties(p);
    PropertiesConfig config=new PropertiesConfig();
    AbstractConfig.appendProperties(config);
    TestCase.assertEquals("dubbo",config.getStr());
  }
  finally {
    System.clearProperty(Constants.DUBBO_PROPERTIES_KEY);
    ConfigUtils.setProperties(null);
  }
}
