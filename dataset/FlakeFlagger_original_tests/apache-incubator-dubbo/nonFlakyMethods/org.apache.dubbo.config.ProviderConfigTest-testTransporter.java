@Test public void testTransporter() throws Exception {
  ProviderConfig provider=new ProviderConfig();
  provider.setTransporter("mocktransporter");
  assertThat(provider.getTransporter(),equalTo("mocktransporter"));
}
