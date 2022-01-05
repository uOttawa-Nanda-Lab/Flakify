/** 
 * Verify response in  {@link File}
 * @throws Exception
 */
@Test public void receiveFile() throws Exception {
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
  File output=File.createTempFile("output",".txt");
  assertTrue(post(url).receive(output).ok());
  StringBuilder buffer=new StringBuilder();
  BufferedReader reader=new BufferedReader(new FileReader(output));
  int read;
  while ((read=reader.read()) != -1)   buffer.append((char)read);
  reader.close();
  assertEquals("content",buffer.toString());
}
