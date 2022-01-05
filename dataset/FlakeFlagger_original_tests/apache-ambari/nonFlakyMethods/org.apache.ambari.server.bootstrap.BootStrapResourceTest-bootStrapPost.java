@Test public void bootStrapPost() throws UniformInterfaceException, JSONException {
  WebResource webResource=resource();
  JSONObject object=webResource.path("/bootstrap").type(MediaType.APPLICATION_JSON).post(JSONObject.class,createDummySshInfo());
  Assert.assertEquals("OK",object.get("status"));
}
