@Test public void testRegistry() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  RegistryConfig registry=new RegistryConfig();
  application.setRegistry(registry);
  assertThat(application.getRegistry(),sameInstance(registry));
  application.setRegistries(Collections.singletonList(registry));
  assertThat(application.getRegistries(),contains(registry));
  assertThat(application.getRegistries(),hasSize(1));
}
