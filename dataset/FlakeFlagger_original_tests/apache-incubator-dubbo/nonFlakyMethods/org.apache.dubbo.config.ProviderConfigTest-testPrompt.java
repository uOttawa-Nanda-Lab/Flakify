@Test public void testPrompt() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setPrompt("#");
  Map<String,String> parameters=new HashMap<String,String>();
  ProviderConfig.appendParameters(parameters,provider);
  assertThat(provider.getPrompt(),equalTo("#"));
  assertThat(parameters,hasEntry("prompt","%23"));
}
