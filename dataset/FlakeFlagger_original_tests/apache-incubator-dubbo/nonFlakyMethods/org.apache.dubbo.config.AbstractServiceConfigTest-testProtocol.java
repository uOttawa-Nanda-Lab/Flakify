@Test public void testProtocol() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  assertThat(serviceConfig.getProtocol(),nullValue());
  serviceConfig.setProtocol(new ProtocolConfig());
  assertThat(serviceConfig.getProtocol(),notNullValue());
  serviceConfig.setProtocols(Collections.singletonList(new ProtocolConfig()));
  assertThat(serviceConfig.getProtocols(),hasSize(1));
}
