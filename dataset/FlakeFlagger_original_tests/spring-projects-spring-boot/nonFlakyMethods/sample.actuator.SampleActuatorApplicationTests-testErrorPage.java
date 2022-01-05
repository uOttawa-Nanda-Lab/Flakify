@Test public void testErrorPage() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port + "/foo",String.class);
  assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,entity.getStatusCode());
  String body=entity.getBody();
  assertNotNull(body);
  assertTrue("Wrong body: " + body,body.contains("\"error\":"));
}
