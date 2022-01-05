@Test public void testRegistry() throws Exception {
  ModuleConfig module=new ModuleConfig();
  RegistryConfig registry=new RegistryConfig();
  module.setRegistry(registry);
  assertThat(module.getRegistry(),sameInstance(registry));
}
