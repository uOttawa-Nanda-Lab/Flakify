@Test public void testLoginPage() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port + "/login",HttpMethod.GET,new HttpEntity<Void>(headers),String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong content:\n" + entity.getBody(),entity.getBody().contains("_csrf"));
}
