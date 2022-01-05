@Test public void testWait() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setWait(10);
  assertThat(registry.getWait(),is(10));
  assertThat(System.getProperty(Constants.SHUTDOWN_WAIT_KEY),equalTo("10"));
}
