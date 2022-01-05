@Test public void basic() throws Exception {
  loadConfig(ClassicTestConstants.JORAN_INPUT_PREFIX + "unique.xml");
  CachingDateFormatter sdf=new CachingDateFormatter("yyyyMMdd'T'HHmmss");
  String timestamp=sdf.format(System.currentTimeMillis());
  sc.assertIsErrorFree();
  Logger root=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  root.info("hello");
  ScaffoldingForRollingTests.existenceCheck(CoreTestConstants.OUTPUT_DIR_PREFIX + "TS_" + timestamp+ "log.txt");
}
