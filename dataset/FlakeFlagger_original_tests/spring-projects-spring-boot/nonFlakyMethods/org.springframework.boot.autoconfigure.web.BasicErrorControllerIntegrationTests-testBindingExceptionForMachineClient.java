@Test @SuppressWarnings("rawtypes") public void testBindingExceptionForMachineClient() throws Exception {
  RequestEntity request=RequestEntity.get(URI.create("http://localhost:" + this.port + "/bind")).accept(MediaType.APPLICATION_JSON).build();
  ResponseEntity<Map> entity=new TestRestTemplate().exchange(request,Map.class);
  String resp=entity.getBody().toString();
  assertThat(resp,containsString("Error count: 1"));
  assertThat(resp,containsString("errors=[{"));
  assertThat(resp,containsString("codes=["));
  assertThat(resp,containsString("org.springframework.validation.BindException"));
}
