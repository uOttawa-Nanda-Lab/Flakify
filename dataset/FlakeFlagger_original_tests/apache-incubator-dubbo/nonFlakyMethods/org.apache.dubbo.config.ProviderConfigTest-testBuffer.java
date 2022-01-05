@Test public void testBuffer() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setBuffer(10);
  assertThat(provider.getBuffer(),is(10));
}
