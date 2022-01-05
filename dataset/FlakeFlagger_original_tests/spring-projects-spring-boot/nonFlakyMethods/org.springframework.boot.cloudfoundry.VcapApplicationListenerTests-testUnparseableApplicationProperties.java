@Test public void testUnparseableApplicationProperties(){
  EnvironmentTestUtils.addEnvironment(this.context,"VCAP_APPLICATION:");
  this.initializer.onApplicationEvent(this.event);
  assertNull(this.context.getEnvironment().getProperty("vcap"));
}
