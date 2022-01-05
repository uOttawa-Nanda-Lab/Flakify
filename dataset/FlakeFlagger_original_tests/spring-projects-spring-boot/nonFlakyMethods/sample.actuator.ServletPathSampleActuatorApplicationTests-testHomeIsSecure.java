@Test public void testHomeIsSecure() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/spring/",Map.class);
  assertEquals(HttpStatus.UNAUTHORIZED,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertEquals("Wrong body: " + body,"Unauthorized",body.get("error"));
  assertFalse("Wrong headers: " + entity.getHeaders(),entity.getHeaders().containsKey("Set-Cookie"));
}
