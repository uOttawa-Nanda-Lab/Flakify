@Test public void testRegister() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setRegister(true);
  assertThat(serviceConfig.isRegister(),is(true));
}
