@Test public void testParameters() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setParameters(Collections.singletonMap("k1","v1"));
  assertThat(registry.getParameters(),hasEntry("k1","v1"));
  Map<String,String> parameters=new HashMap<String,String>();
  RegistryConfig.appendParameters(parameters,registry);
  assertThat(parameters,hasEntry("k1","v1"));
}
