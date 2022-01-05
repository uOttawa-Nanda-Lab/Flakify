@Test public void testExternalTagLibOnly() throws Exception {
  try (CloseableHttpClient httpClient=HttpClients.createDefault()){
    HttpGet httpget=new HttpGet(external_dependency_only_url.toExternalForm() + TEST_JSP);
    HttpResponse response=httpClient.execute(httpget);
    HttpEntity entity=response.getEntity();
    String result=EntityUtils.toString(entity);
    Assert.assertTrue(result,result.contains("External Tag!"));
  }
 }
