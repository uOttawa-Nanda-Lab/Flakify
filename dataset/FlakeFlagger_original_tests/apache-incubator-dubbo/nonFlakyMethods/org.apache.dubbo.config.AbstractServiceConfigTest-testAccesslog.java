@Test public void testAccesslog() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setAccesslog("access.log");
  assertThat(serviceConfig.getAccesslog(),equalTo("access.log"));
  serviceConfig.setAccesslog((Boolean)null);
  assertThat(serviceConfig.getAccesslog(),nullValue());
  serviceConfig.setAccesslog(true);
  assertThat(serviceConfig.getAccesslog(),equalTo("true"));
}
