@Test public void testHtmlErrorPage() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  HttpEntity<?> request=new HttpEntity<Void>(headers);
  ResponseEntity<String> entity=new TestRestTemplate("user",getPassword()).exchange("http://localhost:" + this.port + "/foo",HttpMethod.GET,request,String.class);
  assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,entity.getStatusCode());
  String body=entity.getBody();
  assertNotNull("Body was null",body);
  assertTrue("Wrong body: " + body,body.contains("This application has no explicit mapping for /error"));
}
