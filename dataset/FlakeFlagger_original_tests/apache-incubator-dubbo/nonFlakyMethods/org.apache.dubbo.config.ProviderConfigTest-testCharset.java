@Test public void testCharset() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setCharset("utf-8");
  assertThat(provider.getCharset(),equalTo("utf-8"));
}
