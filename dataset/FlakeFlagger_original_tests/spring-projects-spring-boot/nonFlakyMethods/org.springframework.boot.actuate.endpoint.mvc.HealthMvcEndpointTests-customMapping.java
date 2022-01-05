@SuppressWarnings("unchecked") @Test public void customMapping(){
  given(this.endpoint.invoke()).willReturn(new Health.Builder().status("OK").build());
  this.mvc.setStatusMapping(Collections.singletonMap("OK",HttpStatus.INTERNAL_SERVER_ERROR));
  Object result=this.mvc.invoke(null);
  assertTrue(result instanceof ResponseEntity);
  ResponseEntity<Health> response=(ResponseEntity<Health>)result;
  assertTrue(response.getBody().getStatus().equals(new Status("OK")));
  assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
}
