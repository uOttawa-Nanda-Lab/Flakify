/** 
 * Covers test case when there is EAR with enabled session sharing
 */
@Test @OperateOnDeployment(EAR_DEPLOYMENT_SHARED_SESSIONS) public void testSharedSessionsOneEar() throws IOException {
  HttpClient client=TestHttpClientUtils.promiscuousCookieHttpClient();
  HttpGet get1=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/war1/SharedSessionServlet");
  HttpGet get2=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/war2/SharedSessionServlet");
  String result=runGet(get1,client);
  assertEquals("0",result);
  result=runGet(get1,client);
  assertEquals("1",result);
  result=runGet(get2,client);
  assertEquals("2",result);
  result=runGet(get2,client);
  assertEquals("3",result);
  result=runGet(get1,client);
  assertEquals("4",result);
  HttpClientUtils.closeQuietly(client);
}
