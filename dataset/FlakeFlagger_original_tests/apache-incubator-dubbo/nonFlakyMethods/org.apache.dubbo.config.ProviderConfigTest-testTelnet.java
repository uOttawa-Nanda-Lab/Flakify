@Test public void testTelnet() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setTelnet("mocktelnethandler");
  assertThat(provider.getTelnet(),equalTo("mocktelnethandler"));
}
