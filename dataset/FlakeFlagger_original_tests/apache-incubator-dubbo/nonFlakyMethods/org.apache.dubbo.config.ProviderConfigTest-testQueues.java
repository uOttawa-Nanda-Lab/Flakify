@Test public void testQueues() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setQueues(10);
  assertThat(provider.getQueues(),is(10));
}
