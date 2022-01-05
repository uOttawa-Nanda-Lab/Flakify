@Test public void testExchanger() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setExchanger("mockexchanger");
  assertThat(provider.getExchanger(),equalTo("mockexchanger"));
}
