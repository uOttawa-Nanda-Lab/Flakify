@Test public void testCss() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/webjars/bootstrap/3.0.3/css/bootstrap.min.css",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body:\n" + entity.getBody(),entity.getBody().contains("body"));
  assertEquals("Wrong content type:\n" + entity.getHeaders().getContentType(),MediaType.valueOf("text/css"),entity.getHeaders().getContentType());
}
