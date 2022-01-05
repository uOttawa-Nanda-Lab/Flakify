@Test public void testClient() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setClient("client");
  assertThat(registry.getClient(),equalTo("client"));
}
