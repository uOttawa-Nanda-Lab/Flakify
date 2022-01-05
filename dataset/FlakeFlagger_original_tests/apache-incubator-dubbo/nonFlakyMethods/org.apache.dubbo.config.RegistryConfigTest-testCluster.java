@Test public void testCluster() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setCluster("cluster");
  assertThat(registry.getCluster(),equalTo("cluster"));
}
