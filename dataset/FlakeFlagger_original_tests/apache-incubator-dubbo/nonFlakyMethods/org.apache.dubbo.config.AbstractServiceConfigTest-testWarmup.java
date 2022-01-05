@Test public void testWarmup() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setWarmup(100);
  assertThat(serviceConfig.getWarmup(),equalTo(100));
}
