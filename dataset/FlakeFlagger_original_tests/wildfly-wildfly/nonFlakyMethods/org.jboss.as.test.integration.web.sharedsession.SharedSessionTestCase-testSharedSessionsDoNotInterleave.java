/** 
 * Covers test case when there is one ear with shared sessions between wars and second without sharing. This test checks that the sessions sharing in one EAR doesn't intervene with sessions in second EAR
 */
@Test public void testSharedSessionsDoNotInterleave() throws IOException {
  HttpClient client=TestHttpClientUtils.promiscuousCookieHttpClient();
  HttpGet get1=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/war1/SharedSessionServlet");
  HttpGet get2=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/war2/SharedSessionServlet");
  HttpGet getX=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/warX/SharedSessionServlet");
  HttpGet getY=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/warY/SharedSessionServlet");
  String result=runGet(get1,client);
  assertEquals("0",result);
  result=runGet(get2,client);
  assertEquals("1",result);
  result=runGet(getX,client);
  assertEquals("0",result);
  result=runGet(getY,client);
  assertEquals("0",result);
  result=runGet(get1,client);
  assertEquals("2",result);
  HttpClientUtils.closeQuietly(client);
}
