@Test public void testHost() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setHost("demo-host");
  Map<String,String> parameters=new HashMap<String,String>();
  ProviderConfig.appendParameters(parameters,provider);
  assertThat(provider.getHost(),equalTo("demo-host"));
  assertThat(parameters,not(hasKey("host")));
}
