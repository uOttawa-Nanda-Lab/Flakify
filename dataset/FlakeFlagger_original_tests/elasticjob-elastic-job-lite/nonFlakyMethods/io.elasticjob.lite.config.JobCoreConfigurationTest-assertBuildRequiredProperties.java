@Test public void assertBuildRequiredProperties(){
  JobCoreConfiguration actual=JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build();
  assertRequiredProperties(actual);
  assertDefaultValues(actual);
}
