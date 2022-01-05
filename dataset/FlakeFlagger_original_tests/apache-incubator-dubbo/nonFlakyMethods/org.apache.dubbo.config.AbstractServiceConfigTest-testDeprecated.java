@Test public void testDeprecated() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setDeprecated(true);
  assertThat(serviceConfig.isDeprecated(),is(true));
}
