@Test public void testDispatcher() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setDispatcher("mockdispatcher");
  assertThat(provider.getDispatcher(),equalTo("mockdispatcher"));
}
