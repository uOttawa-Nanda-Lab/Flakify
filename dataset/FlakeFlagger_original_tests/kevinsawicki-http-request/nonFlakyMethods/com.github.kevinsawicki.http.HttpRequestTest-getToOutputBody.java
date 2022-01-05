/** 
 * Make a GET request and get the body using an  {@link AtomicReference}
 * @throws Exception
 */
@Test public void getToOutputBody() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      try {
        response.getWriter().print("hello world");
      }
 catch (      IOException e) {
        fail();
      }
    }
  }
;
  AtomicReference<String> body=new AtomicReference<String>(null);
  get(url).body(body);
  assertEquals("hello world",body.get());
}
