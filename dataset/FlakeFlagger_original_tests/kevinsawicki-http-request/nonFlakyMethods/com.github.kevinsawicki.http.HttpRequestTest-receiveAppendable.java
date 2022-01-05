/** 
 * Verify response in  {@link Appendable}
 * @throws Exception
 */
@Test public void receiveAppendable() throws Exception {
  final StringBuilder body=new StringBuilder();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      try {
        response.getWriter().print("content");
      }
 catch (      IOException e) {
        fail();
      }
    }
  }
;
  assertTrue(post(url).receive(body).ok());
  assertEquals("content",body.toString());
}
