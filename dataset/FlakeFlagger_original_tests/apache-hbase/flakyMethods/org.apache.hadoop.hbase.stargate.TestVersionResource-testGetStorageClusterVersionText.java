public void testGetStorageClusterVersionText() throws IOException {
  Response response=client.get("/version/cluster",MIMETYPE_PLAIN);
  assertTrue(response.getCode() == 200);
}
