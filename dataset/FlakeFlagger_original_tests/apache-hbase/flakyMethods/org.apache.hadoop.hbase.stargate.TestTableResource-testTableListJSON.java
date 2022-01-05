public void testTableListJSON() throws IOException {
  Response response=client.get("/",MIMETYPE_JSON);
  assertEquals(response.getCode(),200);
}
