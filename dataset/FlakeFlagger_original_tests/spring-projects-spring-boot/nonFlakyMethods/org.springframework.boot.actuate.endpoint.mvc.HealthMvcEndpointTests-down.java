@SuppressWarnings("unchecked") @Test public void down(){
  given(this.endpoint.invoke()).willReturn(new Health.Builder().down().build());
  Object result=this.mvc.invoke(null);
  assertTrue(result instanceof ResponseEntity);
  ResponseEntity<Health> response=(ResponseEntity<Health>)result;
  assertTrue(response.getBody().getStatus() == Status.DOWN);
  assertEquals(HttpStatus.SERVICE_UNAVAILABLE,response.getStatusCode());
}
