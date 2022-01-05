@Test public void testSubscribe() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setSubscribe(true);
  assertThat(registry.isSubscribe(),is(true));
}
