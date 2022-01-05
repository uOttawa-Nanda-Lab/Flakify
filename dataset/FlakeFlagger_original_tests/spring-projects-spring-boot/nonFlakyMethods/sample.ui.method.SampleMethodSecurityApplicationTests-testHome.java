@Test public void testHome() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port,HttpMethod.GET,new HttpEntity<Void>(headers),String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body (title doesn't match):\n" + entity.getBody(),entity.getBody().contains("<title>Login"));
}
