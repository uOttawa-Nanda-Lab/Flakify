@Test public void testPath() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setPath("/path");
  Map<String,String> parameters=new HashMap<String,String>();
  ProviderConfig.appendParameters(parameters,provider);
  assertThat(provider.getPath(),equalTo("/path"));
  assertThat(provider.getContextpath(),equalTo("/path"));
  assertThat(parameters,not(hasKey("path")));
}
