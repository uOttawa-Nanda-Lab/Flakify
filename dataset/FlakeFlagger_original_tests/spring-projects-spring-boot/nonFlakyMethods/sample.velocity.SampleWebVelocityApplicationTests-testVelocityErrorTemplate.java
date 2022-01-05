@Test public void testVelocityErrorTemplate() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  HttpEntity<String> requestEntity=new HttpEntity<String>(headers);
  ResponseEntity<String> responseEntity=new TestRestTemplate().exchange("http://localhost:" + this.port + "/does-not-exist",HttpMethod.GET,requestEntity,String.class);
  assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
  assertTrue("Wrong body:\n" + responseEntity.getBody(),responseEntity.getBody().contains("Something went wrong: 404 Not Found"));
}
