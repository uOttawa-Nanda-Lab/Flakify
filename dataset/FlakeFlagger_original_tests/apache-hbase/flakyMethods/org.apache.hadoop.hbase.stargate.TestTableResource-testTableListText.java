public void testTableListText() throws IOException {
  Response response=client.get("/",MIMETYPE_PLAIN);
  assertEquals(response.getCode(),200);
}
