@Test public void testSunnyDayMandated(){
  Assume.assumeNotNull(System.getProperty("file.encoding"));
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.mandatory_file_encoding:" + System.getProperty("file.encoding"));
  this.initializer.onApplicationEvent(this.event);
}
