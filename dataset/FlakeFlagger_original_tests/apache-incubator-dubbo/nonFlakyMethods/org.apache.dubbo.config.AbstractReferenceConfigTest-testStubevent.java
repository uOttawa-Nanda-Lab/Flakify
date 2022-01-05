@Test public void testStubevent() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setOnconnect("onConnect");
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractInterfaceConfig.appendParameters(parameters,referenceConfig);
  assertThat(parameters,hasKey(Constants.STUB_EVENT_KEY));
}
