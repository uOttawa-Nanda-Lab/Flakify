@Test public void testOnconnect() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setOnconnect("onConnect");
  assertThat(referenceConfig.getOnconnect(),equalTo("onConnect"));
  assertThat(referenceConfig.getStubevent(),is(true));
}
