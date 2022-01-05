@Test public void testStatus() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setStatus("mockstatuschecker");
  assertThat(provider.getStatus(),equalTo("mockstatuschecker"));
}
