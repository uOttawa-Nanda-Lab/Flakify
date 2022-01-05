@Test public void sysProps() throws JoranException {
  System.setProperty("k.lbcore254",ClassicTestConstants.ISSUES_PREFIX + "lbcore254");
  JoranConfigurator configurator=new JoranConfigurator();
  configurator.setContext(loggerContext);
  configurator.doConfigure(ClassicTestConstants.ISSUES_PREFIX + "lbcore254.xml");
  checker.assertIsErrorFree();
}
