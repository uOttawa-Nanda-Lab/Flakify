@Test public void testLogin() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.set("username","admin");
  form.set("password","admin");
  getCsrf(form,headers);
  ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port + "/login",HttpMethod.POST,new HttpEntity<MultiValueMap<String,String>>(form,headers),String.class);
  assertEquals(HttpStatus.FOUND,entity.getStatusCode());
  assertEquals("http://localhost:" + this.port + "/",entity.getHeaders().getLocation().toString());
}
