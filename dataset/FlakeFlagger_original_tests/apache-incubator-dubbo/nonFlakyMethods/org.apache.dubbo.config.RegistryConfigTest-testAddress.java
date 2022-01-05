@Test public void testAddress() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setAddress("localhost");
  assertThat(registry.getAddress(),equalTo("localhost"));
  Map<String,String> parameters=new HashMap<String,String>();
  RegistryConfig.appendParameters(parameters,registry);
  assertThat(parameters,not(hasKey("address")));
}
