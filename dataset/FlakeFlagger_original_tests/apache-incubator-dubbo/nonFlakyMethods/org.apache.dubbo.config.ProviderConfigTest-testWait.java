@Test public void testWait() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setWait(10);
  assertThat(provider.getWait(),equalTo(10));
}
