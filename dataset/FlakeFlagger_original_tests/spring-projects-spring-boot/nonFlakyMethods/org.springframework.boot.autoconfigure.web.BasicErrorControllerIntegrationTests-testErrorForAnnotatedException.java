@Test @SuppressWarnings("rawtypes") public void testErrorForAnnotatedException() throws Exception {
  ResponseEntity<Map> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/annotated",Map.class);
  assertThat(entity.getBody().toString(),endsWith("status=400, " + "error=Bad Request, " + "exception=org.springframework.boot.autoconfigure.web.BasicErrorControllerIntegrationTests$TestConfiguration$Errors$ExpectedException, "+ "message=Expected!, "+ "path=/annotated}"));
}
