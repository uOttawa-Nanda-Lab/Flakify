/** 
 * Make a GET request with basic authentication specified
 * @throws Exception
 */
@Test public void basicAuthentication() throws Exception {
  final AtomicReference<String> user=new AtomicReference<String>();
  final AtomicReference<String> password=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      String auth=request.getHeader("Authorization");
      auth=auth.substring(auth.indexOf(' ') + 1);
      try {
        auth=B64Code.decode(auth,CHARSET_UTF8);
      }
 catch (      UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
      int colon=auth.indexOf(':');
      user.set(auth.substring(0,colon));
      password.set(auth.substring(colon + 1));
      response.setStatus(HTTP_OK);
    }
  }
;
  assertTrue(get(url).basic("user","p4ssw0rd").ok());
  assertEquals("user",user.get());
  assertEquals("p4ssw0rd",password.get());
}
