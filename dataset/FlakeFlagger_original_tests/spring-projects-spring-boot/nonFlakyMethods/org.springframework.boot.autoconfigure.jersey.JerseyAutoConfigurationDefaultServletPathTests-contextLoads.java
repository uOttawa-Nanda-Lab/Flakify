@Test public void contextLoads(){
  ResponseEntity<String> entity=this.restTemplate.getForEntity("http://localhost:" + this.port + "/hello",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
}
