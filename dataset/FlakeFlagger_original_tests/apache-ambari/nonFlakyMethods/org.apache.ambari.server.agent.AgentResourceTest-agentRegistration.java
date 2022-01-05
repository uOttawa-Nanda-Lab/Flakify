@Test public void agentRegistration() throws UniformInterfaceException, JSONException {
  RegistrationResponse response;
  ClientConfig clientConfig=new DefaultClientConfig();
  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
  client=Client.create(clientConfig);
  WebResource webResource=client.resource("http://localhost:9998/register/dummyhost");
  response=webResource.type(MediaType.APPLICATION_JSON).post(RegistrationResponse.class,createDummyJSONRegister());
  LOG.info("Returned from Server responce=" + response);
  Assert.assertEquals(response.getResponseStatus(),RegistrationStatus.OK);
}
