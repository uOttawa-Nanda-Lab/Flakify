@Test public void bootStrapGet() throws UniformInterfaceException, JSONException {
  WebResource webResource=resource();
  BootStrapStatus status=webResource.path("/bootstrap/0").type(MediaType.APPLICATION_JSON).get(BootStrapStatus.class);
  LOG.info("GET Response from the API " + status.getLog() + " "+ status.getStatus());
  Assert.assertEquals(status.getStatus(),BSStat.ERROR);
}
