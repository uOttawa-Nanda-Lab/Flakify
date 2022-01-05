@Test public void testContextPath() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setContextpath("/context-path");
  Map<String,String> parameters=new HashMap<String,String>();
  ProviderConfig.appendParameters(parameters,provider);
  assertThat(provider.getContextpath(),equalTo("/context-path"));
  assertThat(parameters,not(hasKey("/context-path")));
}
