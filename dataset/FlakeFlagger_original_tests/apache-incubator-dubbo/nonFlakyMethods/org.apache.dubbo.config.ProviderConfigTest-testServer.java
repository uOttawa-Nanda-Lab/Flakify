@Test public void testServer() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setServer("demo-server");
  assertThat(provider.getServer(),equalTo("demo-server"));
}
