@Test public void testSimpleBeanInjected() throws Exception {
  try (CloseableHttpClient httpclient=HttpClients.createDefault()){
    HttpGet httpget=new HttpGet(url.toExternalForm() + "/servlet");
    HttpResponse response=httpclient.execute(httpget);
    HttpEntity entity=response.getEntity();
    StatusLine statusLine=response.getStatusLine();
    assertEquals(200,statusLine.getStatusCode());
    String result=EntityUtils.toString(entity);
    Assert.assertEquals(ModuleServlet.MODULE_SERVLET,result);
  }
 }
