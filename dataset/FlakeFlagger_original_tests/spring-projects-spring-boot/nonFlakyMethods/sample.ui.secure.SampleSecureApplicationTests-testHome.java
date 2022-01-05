@Test public void testHome() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port,HttpMethod.GET,new HttpEntity<Void>(headers),String.class);
  assertEquals(HttpStatus.FOUND,entity.getStatusCode());
  assertTrue("Wrong location:\n" + entity.getHeaders(),entity.getHeaders().getLocation().toString().endsWith(port + "/login"));
}
