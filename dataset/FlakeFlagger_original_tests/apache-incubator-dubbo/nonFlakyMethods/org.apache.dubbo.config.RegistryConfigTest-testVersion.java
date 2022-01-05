@Test public void testVersion() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setVersion("1.0.0");
  assertThat(registry.getVersion(),equalTo("1.0.0"));
}
