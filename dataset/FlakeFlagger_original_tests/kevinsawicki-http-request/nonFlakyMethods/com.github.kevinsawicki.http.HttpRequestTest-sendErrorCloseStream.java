/** 
 * Send a stream that throws an exception when read from
 * @throws Exception
 */
@Test public void sendErrorCloseStream() throws Exception {
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
  final IOException closeCause=new IOException();
  InputStream stream=new InputStream(){
    public int read() throws IOException {
      return -1;
    }
    public void close() throws IOException {
      throw closeCause;
    }
  }
;
  try {
    post(url).ignoreCloseExceptions(false).send(stream);
    fail("Exception not thrown");
  }
 catch (  HttpRequestException e) {
    assertEquals(closeCause,e.getCause());
  }
}
