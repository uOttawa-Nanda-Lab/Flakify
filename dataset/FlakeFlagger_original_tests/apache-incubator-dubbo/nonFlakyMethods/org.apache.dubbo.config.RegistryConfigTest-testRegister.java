@Test public void testRegister() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setRegister(true);
  assertThat(registry.isRegister(),is(true));
}
