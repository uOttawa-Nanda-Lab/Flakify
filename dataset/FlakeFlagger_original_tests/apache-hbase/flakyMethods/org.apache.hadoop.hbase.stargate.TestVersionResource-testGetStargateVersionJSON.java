public void testGetStargateVersionJSON() throws IOException {
  Response response=client.get("/version",MIMETYPE_JSON);
  assertTrue(response.getCode() == 200);
}
