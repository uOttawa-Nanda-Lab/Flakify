@Test public void testStaticPage() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/index.html",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  String body=entity.getBody();
  assertTrue("Wrong body:\n" + body,body.contains("<html>"));
  assertTrue("Wrong body:\n" + body,body.contains("<h1>Hello</h1>"));
}
