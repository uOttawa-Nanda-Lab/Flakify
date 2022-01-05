@Test public void testThreadpool() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setThreadpool("mockthreadpool");
  assertThat(provider.getThreadpool(),equalTo("mockthreadpool"));
}
