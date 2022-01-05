@Test public void testVersion() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setVersion("version");
  assertThat(serviceConfig.getVersion(),equalTo("version"));
}
