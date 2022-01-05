@Test public void testProtocol() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setProtocol("protocol");
  assertThat(provider.getProtocol().getName(),equalTo("protocol"));
}
