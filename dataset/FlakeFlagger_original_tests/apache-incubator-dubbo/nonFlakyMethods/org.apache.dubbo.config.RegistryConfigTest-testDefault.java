@Test public void testDefault() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setDefault(true);
  assertThat(registry.isDefault(),is(true));
}
