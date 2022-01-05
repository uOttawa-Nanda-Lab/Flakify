@Test public void testReconnect() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setReconnect("reconnect");
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractInterfaceConfig.appendParameters(parameters,referenceConfig);
  assertThat(referenceConfig.getReconnect(),equalTo("reconnect"));
  assertThat(parameters,hasKey(Constants.RECONNECT_KEY));
}
