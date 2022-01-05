@Test @SuppressWarnings("rawtypes") public void testErrorForMachineClient() throws Exception {
  ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port,Map.class);
  assertThat(entity.getBody().toString(),endsWith("status=500, " + "error=Internal Server Error, " + "exception=java.lang.IllegalStateException, "+ "message=Server Error, "+ "path=/}"));
}
