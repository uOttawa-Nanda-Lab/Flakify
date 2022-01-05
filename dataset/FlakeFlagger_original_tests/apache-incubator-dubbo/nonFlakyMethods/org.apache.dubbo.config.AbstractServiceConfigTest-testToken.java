@Test public void testToken() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setToken("token");
  assertThat(serviceConfig.getToken(),equalTo("token"));
  serviceConfig.setToken((Boolean)null);
  assertThat(serviceConfig.getToken(),nullValue());
  serviceConfig.setToken(true);
  assertThat(serviceConfig.getToken(),is("true"));
}
