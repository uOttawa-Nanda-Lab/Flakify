@Test public void testDefault() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setDefault(true);
  Map<String,String> parameters=new HashMap<String,String>();
  ProviderConfig.appendParameters(parameters,provider);
  assertThat(provider.isDefault(),is(true));
  assertThat(parameters,not(hasKey("default")));
}
