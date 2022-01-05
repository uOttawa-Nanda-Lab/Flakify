@Test public void testError() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port + "/error",HttpMethod.GET,new HttpEntity<Void>(headers),String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body:\n" + entity.getBody(),entity.getBody().contains("<html>"));
  assertTrue("Wrong body:\n" + entity.getBody(),entity.getBody().contains("<body>"));
  assertTrue("Wrong body:\n" + entity.getBody(),entity.getBody().contains("Please contact the operator with the above information"));
}
