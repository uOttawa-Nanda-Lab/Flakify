@Test public void testFileUpload() throws Exception {
  TestHttpClient client=new TestHttpClient();
  try {
    HttpPost post=new HttpPost(DefaultServer.getDefaultServerURL() + "/path");
    MultipartEntity entity=new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
    entity.addPart("formValue",new StringBody("myValue","text/plain",Charset.forName("UTF-8")));
    entity.addPart("file",new FileBody(new File(MultipartFormDataParserTestCase.class.getResource("uploadfile.txt").getFile())));
    post.setEntity(entity);
    HttpResponse result=client.execute(post);
    Assert.assertEquals(200,result.getStatusLine().getStatusCode());
    HttpClientUtils.readResponse(result);
  }
  finally {
    client.getConnectionManager().shutdown();
  }
}
