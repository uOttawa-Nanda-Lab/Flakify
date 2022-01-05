/** 
 * Make a GET and get response as a input stream reader
 * @throws Exception
 */
@Test public void getReader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      write("hello");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  BufferedReader reader=new BufferedReader(request.reader());
  assertEquals("hello",reader.readLine());
  reader.close();
}
