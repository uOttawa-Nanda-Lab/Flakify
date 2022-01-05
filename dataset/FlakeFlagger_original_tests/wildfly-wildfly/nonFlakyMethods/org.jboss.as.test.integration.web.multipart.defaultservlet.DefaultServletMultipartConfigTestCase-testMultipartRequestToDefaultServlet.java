@Test public void testMultipartRequestToDefaultServlet() throws Exception {
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpPost post=new HttpPost(url.toExternalForm() + "/servlet");
    post.setEntity(MultipartEntityBuilder.create().addTextBody("file",MESSAGE).build());
    HttpResponse response=httpClient.execute(post);
    HttpEntity entity=response.getEntity();
    StatusLine statusLine=response.getStatusLine();
    assertEquals(200,statusLine.getStatusCode());
    String result=EntityUtils.toString(entity);
    Assert.assertEquals(MESSAGE,result);
  }
 }
