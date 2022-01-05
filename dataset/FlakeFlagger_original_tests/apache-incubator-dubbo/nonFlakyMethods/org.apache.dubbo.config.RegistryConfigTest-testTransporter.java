@Test public void testTransporter() throws Exception {
  RegistryConfig registry=new RegistryConfig();
  registry.setTransporter("transporter");
  assertThat(registry.getTransporter(),equalTo("transporter"));
}
