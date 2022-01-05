@Test public void testDelay() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setDelay(1000);
  assertThat(serviceConfig.getDelay(),equalTo(1000));
}
