@Test public void testNetworker() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setNetworker("networker");
  assertThat(provider.getNetworker(),equalTo("networker"));
}
