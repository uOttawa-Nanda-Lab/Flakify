@Test public void disabled(){
  @SuppressWarnings("unchecked") ResponseEntity<Map<String,String>> response=(ResponseEntity<Map<String,String>>)this.mvc.invoke();
  assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
}
