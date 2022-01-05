@Test public void agentHeartBeat() throws UniformInterfaceException, JSONException {
  HeartBeatResponse response;
  ClientConfig clientConfig=new DefaultClientConfig();
  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
  client=Client.create(clientConfig);
  WebResource webResource=client.resource("http://localhost:9998/heartbeat/dummyhost");
  response=webResource.type(MediaType.APPLICATION_JSON).post(HeartBeatResponse.class,createDummyHeartBeat());
  LOG.info("Returned from Server: " + " response=" + response);
  Assert.assertEquals(response.getResponseId(),0L);
}
