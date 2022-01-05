/** 
 * Verify response via a  {@link PrintStream}
 * @throws Exception
 */
@Test public void receivePrintStream() throws Exception {
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
  ByteArrayOutputStream output=new ByteArrayOutputStream();
  PrintStream stream=new PrintStream(output,true,CHARSET_UTF8);
  assertTrue(post(url).receive(stream).ok());
  stream.close();
  assertEquals("content",output.toString(CHARSET_UTF8));
}
