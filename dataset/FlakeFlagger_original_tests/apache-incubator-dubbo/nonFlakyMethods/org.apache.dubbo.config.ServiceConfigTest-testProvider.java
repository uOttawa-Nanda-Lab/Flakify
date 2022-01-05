@Test public void testProvider() throws Exception {
  ServiceConfig service=new ServiceConfig();
  ProviderConfig provider=new ProviderConfig();
  service.setProvider(provider);
  assertThat(service.getProvider(),is(provider));
}
