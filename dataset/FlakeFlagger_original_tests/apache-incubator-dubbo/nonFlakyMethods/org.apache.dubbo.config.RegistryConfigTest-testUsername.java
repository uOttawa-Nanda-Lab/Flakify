@Test public void testUsername() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setUsername("username");
  assertThat(registry.getUsername(),equalTo("username"));
}
