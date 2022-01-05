@Test public void testListener() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setListener("mockinvokerlistener");
  assertThat(referenceConfig.getListener(),equalTo("mockinvokerlistener"));
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put(Constants.INVOKER_LISTENER_KEY,"prelistener");
  AbstractInterfaceConfig.appendParameters(parameters,referenceConfig);
  assertThat(parameters,hasValue("prelistener,mockinvokerlistener"));
}
