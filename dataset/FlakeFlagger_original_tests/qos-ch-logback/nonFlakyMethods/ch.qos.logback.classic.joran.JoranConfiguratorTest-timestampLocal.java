@Test public void timestampLocal() throws JoranException, IOException, InterruptedException {
  String sysProp="ch.qos.logback.classic.joran.JoranConfiguratorTest.timestampLocal";
  System.setProperty(sysProp,"");
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "timestamp-local.xml";
  configure(configFileAsStr);
  String r=loggerContext.getProperty("testTimestamp");
  assertNull(r);
  String expected="today is " + new SimpleDateFormat("yyyy-MM").format(new Date());
  String sysPropValue=System.getProperty(sysProp);
  assertEquals(expected,sysPropValue);
}
