@Test public void testDenied() throws Exception {
  HttpHeaders headers=new HttpHeaders();
  headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.set("username","user");
  form.set("password","user");
  getCsrf(form,headers);
  ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port + "/login",HttpMethod.POST,new HttpEntity<MultiValueMap<String,String>>(form,headers),String.class);
  assertEquals(HttpStatus.FOUND,entity.getStatusCode());
  String cookie=entity.getHeaders().getFirst("Set-Cookie");
  headers.set("Cookie",cookie);
  ResponseEntity<String> page=new TestRestTemplate().exchange(entity.getHeaders().getLocation(),HttpMethod.GET,new HttpEntity<Void>(headers),String.class);
  assertEquals(HttpStatus.FORBIDDEN,page.getStatusCode());
  assertTrue("Wrong body (message doesn't match):\n" + entity.getBody(),page.getBody().contains("Access denied"));
}
