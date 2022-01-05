@Test public void assertFilterContentWithSensitiveIp(){
  List<String> actual=Arrays.asList("/simpleElasticDemoJob/servers/127.0.0.1","/simpleElasticDemoJob/servers/192.168.0.1/hostName | 192.168.0.1");
  List<String> expected=Arrays.asList("/simpleElasticDemoJob/servers/ip1","/simpleElasticDemoJob/servers/ip2/hostName | ip2");
  assertThat(SensitiveInfoUtils.filterSensitiveIps(actual),is(expected));
}
