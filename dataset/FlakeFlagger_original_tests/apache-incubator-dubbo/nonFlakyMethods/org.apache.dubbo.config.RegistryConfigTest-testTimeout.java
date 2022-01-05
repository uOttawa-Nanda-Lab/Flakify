@Test public void testTimeout() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setTimeout(10);
  assertThat(registry.getTimeout(),is(10));
}
