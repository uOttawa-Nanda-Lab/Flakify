@Test public void testListener() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setListener("mockexporterlistener");
  assertThat(serviceConfig.getListener(),equalTo("mockexporterlistener"));
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put(Constants.EXPORTER_LISTENER_KEY,"prelistener");
  AbstractServiceConfig.appendParameters(parameters,serviceConfig);
  assertThat(parameters,hasEntry(Constants.EXPORTER_LISTENER_KEY,"prelistener,mockexporterlistener"));
}
