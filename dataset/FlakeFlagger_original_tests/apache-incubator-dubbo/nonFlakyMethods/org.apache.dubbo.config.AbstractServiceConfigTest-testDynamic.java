@Test public void testDynamic() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setDynamic(true);
  assertThat(serviceConfig.isDynamic(),is(true));
}
