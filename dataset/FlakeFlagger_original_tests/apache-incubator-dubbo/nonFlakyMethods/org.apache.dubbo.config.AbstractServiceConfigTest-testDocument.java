@Test public void testDocument() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setDocument("http://dubbo.io");
  assertThat(serviceConfig.getDocument(),equalTo("http://dubbo.io"));
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractServiceConfig.appendParameters(parameters,serviceConfig);
  assertThat(parameters,hasEntry("document","http%3A%2F%2Fdubbo.io"));
}
