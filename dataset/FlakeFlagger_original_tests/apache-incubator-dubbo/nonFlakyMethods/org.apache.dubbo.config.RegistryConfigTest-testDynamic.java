@Test public void testDynamic() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setDynamic(true);
  assertThat(registry.isDynamic(),is(true));
}
