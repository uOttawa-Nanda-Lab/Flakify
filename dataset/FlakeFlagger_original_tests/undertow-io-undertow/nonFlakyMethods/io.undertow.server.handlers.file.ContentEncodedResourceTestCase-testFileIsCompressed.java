@Test public void testFileIsCompressed() throws IOException, InterruptedException {
  ContentEncodingHttpClient client=new ContentEncodingHttpClient();
  String fileName="hello.html";
  File f=new File(tmpDir,fileName);
  writeFile(f,"hello world");
  try {
    for (int i=0; i < 3; ++i) {
      HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/" + fileName);
      HttpResponse result=client.execute(get);
      Assert.assertEquals(200,result.getStatusLine().getStatusCode());
      String response=HttpClientUtils.readResponse(result);
      Assert.assertEquals("hello world",response);
      Assert.assertEquals("deflate",result.getHeaders(Headers.CONTENT_ENCODING_STRING)[0].getValue());
    }
    writeFile(f,"modified file");
    HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/" + fileName);
    HttpResponse result=client.execute(get);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    String response=HttpClientUtils.readResponse(result);
    Assert.assertEquals("hello world",response);
    Assert.assertEquals("deflate",result.getHeaders(Headers.CONTENT_ENCODING_STRING)[0].getValue());
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
