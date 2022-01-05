@Test public void assertToJsonForScriptJob(){
  LiteJobConfiguration actual=LiteJobConfiguration.newBuilder(new ScriptJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),"test.sh")).build();
  assertThat(LiteJobConfigurationGsonFactory.toJson(actual),is(scriptJobJson));
}
