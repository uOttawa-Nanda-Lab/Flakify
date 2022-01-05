@Test public void contextLoads(){
  ResponseEntity<String> entity=this.restTemplate.getForEntity("http://localhost:" + this.port + "/rest/hello",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
}
