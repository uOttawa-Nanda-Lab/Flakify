public void testGetStorageClusterVersionJSON() throws IOException {
  Response response=client.get("/version/cluster",MIMETYPE_JSON);
  assertTrue(response.getCode() == 200);
}
