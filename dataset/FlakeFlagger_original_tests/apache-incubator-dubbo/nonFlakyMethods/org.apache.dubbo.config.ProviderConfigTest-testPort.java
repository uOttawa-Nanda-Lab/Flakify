@Test public void testPort() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setPort(8080);
  Map<String,String> parameters=new HashMap<String,String>();
  ProviderConfig.appendParameters(parameters,provider);
  assertThat(provider.getPort(),is(8080));
  assertThat(parameters,not(hasKey("port")));
}
