/** 
 * Verify multipart with file, stream, number, and string parameters
 * @throws Exception
 */
@Test public void postMultipart() throws Exception {
  final StringBuilder body=new StringBuilder();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      char[] buffer=new char[8192];
      int read;
      try {
        while ((read=request.getReader().read(buffer)) != -1)         body.append(buffer,0,read);
      }
 catch (      IOException e) {
        fail();
      }
    }
  }
;
  File file=File.createTempFile("body",".txt");
  File file2=File.createTempFile("body",".txt");
  new FileWriter(file).append("content1").close();
  new FileWriter(file2).append("content4").close();
  HttpRequest request=post(url);
  request.part("description","content2");
  request.part("size",file.length());
  request.part("body",file.getName(),file);
  request.part("file",file2);
  request.part("stream",new ByteArrayInputStream("content3".getBytes()));
  assertTrue(request.ok());
  assertTrue(body.toString().contains("content1\r\n"));
  assertTrue(body.toString().contains("content2\r\n"));
  assertTrue(body.toString().contains("content3\r\n"));
  assertTrue(body.toString().contains("content4\r\n"));
  assertTrue(body.toString().contains(Long.toString(file.length()) + "\r\n"));
}
