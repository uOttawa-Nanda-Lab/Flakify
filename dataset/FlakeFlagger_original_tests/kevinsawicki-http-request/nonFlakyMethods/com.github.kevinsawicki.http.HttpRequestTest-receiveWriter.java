/** 
 * Verify response in  {@link Writer}
 * @throws Exception
 */
@Test public void receiveWriter() throws Exception {
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
  StringWriter writer=new StringWriter();
  assertTrue(post(url).receive(writer).ok());
  assertEquals("content",writer.toString());
}
