@Test public void testProtocol() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setProtocol("protocol");
  assertThat(registry.getProtocol(),equalTo(registry.getProtocol()));
}
