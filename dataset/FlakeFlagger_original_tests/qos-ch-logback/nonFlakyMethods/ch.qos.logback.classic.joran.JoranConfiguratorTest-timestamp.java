@Test public void timestamp() throws JoranException, IOException, InterruptedException {
  String configFileAsStr=ClassicTestConstants.JORAN_INPUT_PREFIX + "timestamp-context.xml";
  configure(configFileAsStr);
  String r=loggerContext.getProperty("testTimestamp");
  assertNotNull(r);
  CachingDateFormatter sdf=new CachingDateFormatter("yyyy-MM");
  String expected=sdf.format(System.currentTimeMillis());
  assertEquals("expected \"" + expected + "\" but got "+ r,expected,r);
}
