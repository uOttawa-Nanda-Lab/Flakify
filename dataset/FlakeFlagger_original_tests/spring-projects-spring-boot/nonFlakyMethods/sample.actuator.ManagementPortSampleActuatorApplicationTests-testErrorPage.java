@Test public void testErrorPage() throws Exception {
  @SuppressWarnings("rawtypes") ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.managementPort + "/error",Map.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  @SuppressWarnings("unchecked") Map<String,Object> body=entity.getBody();
  assertEquals(999,body.get("status"));
}
