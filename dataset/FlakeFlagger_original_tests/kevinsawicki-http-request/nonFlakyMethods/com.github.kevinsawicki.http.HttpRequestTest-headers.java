/** 
 * Verify setting headers
 * @throws Exception
 */
@Test public void headers() throws Exception {
  final AtomicReference<String> h1=new AtomicReference<String>();
  final AtomicReference<String> h2=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      h1.set(request.getHeader("h1"));
      h2.set(request.getHeader("h2"));
    }
  }
;
  Map<String,String> headers=new HashMap<String,String>();
  headers.put("h1","v1");
  headers.put("h2","v2");
  assertTrue(get(url).headers(headers).ok());
  assertEquals("v1",h1.get());
  assertEquals("v2",h2.get());
}
