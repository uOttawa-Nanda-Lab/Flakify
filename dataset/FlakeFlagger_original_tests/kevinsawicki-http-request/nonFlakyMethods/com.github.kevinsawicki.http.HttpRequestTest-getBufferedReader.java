/** 
 * Make a GET and get response as a buffered reader
 * @throws Exception
 */
@Test public void getBufferedReader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      write("hello");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  BufferedReader reader=request.bufferedReader();
  assertEquals("hello",reader.readLine());
  reader.close();
}
