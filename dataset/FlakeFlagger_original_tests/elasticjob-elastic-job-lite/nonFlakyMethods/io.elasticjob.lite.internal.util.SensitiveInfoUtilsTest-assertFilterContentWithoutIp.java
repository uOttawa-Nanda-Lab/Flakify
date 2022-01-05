@Test public void assertFilterContentWithoutIp(){
  List<String> actual=Arrays.asList("/simpleElasticDemoJob/servers","/simpleElasticDemoJob/leader");
  assertThat(SensitiveInfoUtils.filterSensitiveIps(actual),is(actual));
}
