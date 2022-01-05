@Test public void testTrace() throws Exception {
  new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/health",String.class);
  @SuppressWarnings("rawtypes") ResponseEntity<List> entity=new TestRestTemplate("user",getPassword()).getForEntity("http://localhost:" + this.port + "/trace",List.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  @SuppressWarnings("unchecked") List<Map<String,Object>> list=entity.getBody();
  Map<String,Object> trace=list.get(list.size() - 1);
  @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)((Map<String,Object>)((Map<String,Object>)trace.get("info")).get("headers")).get("response");
  assertEquals("200",map.get("status"));
}
