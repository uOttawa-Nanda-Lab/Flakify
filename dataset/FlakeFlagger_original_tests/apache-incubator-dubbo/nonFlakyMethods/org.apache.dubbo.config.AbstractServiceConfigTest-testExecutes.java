@Test public void testExecutes() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setExecutes(10);
  assertThat(serviceConfig.getExecutes(),equalTo(10));
}
