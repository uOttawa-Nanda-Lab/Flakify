@Test public void testVersion() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setVersion("version");
  assertThat(referenceConfig.getVersion(),equalTo("version"));
}
