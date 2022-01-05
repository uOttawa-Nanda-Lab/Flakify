@Test public void testBeans() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<List> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port + "/beans",List.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertEquals(1,entity.getBody().size());
  @SuppressWarnings("unchecked") Map<String,Object> body=(Map<String,Object>)entity.getBody().get(0);
  assertTrue("Wrong body: " + body,((String)body.get("context")).startsWith("application"));
}
