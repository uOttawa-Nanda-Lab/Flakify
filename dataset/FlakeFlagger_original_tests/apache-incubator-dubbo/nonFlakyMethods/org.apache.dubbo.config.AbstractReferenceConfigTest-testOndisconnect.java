@Test public void testOndisconnect() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setOndisconnect("onDisconnect");
  assertThat(referenceConfig.getOndisconnect(),equalTo("onDisconnect"));
  assertThat(referenceConfig.getStubevent(),is(true));
}
