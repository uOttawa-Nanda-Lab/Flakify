@Test public void testExport() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setExport(true);
  assertThat(serviceConfig.getExport(),is(true));
}
