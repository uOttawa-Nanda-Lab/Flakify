@Test public void testGroup() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setGroup("group");
  assertThat(referenceConfig.getGroup(),equalTo("group"));
}
