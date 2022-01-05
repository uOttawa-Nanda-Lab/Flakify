@Test public void testPassword() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setPassword("password");
  assertThat(registry.getPassword(),equalTo("password"));
}
