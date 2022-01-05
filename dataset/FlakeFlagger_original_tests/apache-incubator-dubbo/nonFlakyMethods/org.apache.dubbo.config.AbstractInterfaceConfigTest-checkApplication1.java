@Test public void checkApplication1() throws Exception {
  try {
    ConfigUtils.setProperties(null);
    System.clearProperty(Constants.SHUTDOWN_WAIT_KEY);
    System.clearProperty(Constants.SHUTDOWN_WAIT_SECONDS_KEY);
    writeDubboProperties(Constants.SHUTDOWN_WAIT_KEY,"100");
    System.setProperty("dubbo.application.name","demo");
    InterfaceConfig interfaceConfig=new InterfaceConfig();
    interfaceConfig.checkApplication();
    ApplicationConfig appConfig=interfaceConfig.getApplication();
    TestCase.assertEquals("demo",appConfig.getName());
    TestCase.assertEquals("100",System.getProperty(Constants.SHUTDOWN_WAIT_KEY));
    System.clearProperty(Constants.SHUTDOWN_WAIT_KEY);
    ConfigUtils.setProperties(null);
    writeDubboProperties(Constants.SHUTDOWN_WAIT_SECONDS_KEY,"1000");
    System.setProperty("dubbo.application.name","demo");
    interfaceConfig=new InterfaceConfig();
    interfaceConfig.checkApplication();
    TestCase.assertEquals("1000",System.getProperty(Constants.SHUTDOWN_WAIT_SECONDS_KEY));
  }
  finally {
    ConfigUtils.setProperties(null);
    System.clearProperty("dubbo.application.name");
    System.clearProperty(Constants.SHUTDOWN_WAIT_KEY);
    System.clearProperty(Constants.SHUTDOWN_WAIT_SECONDS_KEY);
  }
}
