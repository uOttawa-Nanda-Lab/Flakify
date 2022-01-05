/** 
 * Covers test case when there is EAR with disabled session sharing
 */
@Test @OperateOnDeployment(EAR_DEPLOYMENT_NOT_SHARED_SESSIONS) public void testNotSharedSessions() throws IOException {
  HttpClient client=TestHttpClientUtils.promiscuousCookieHttpClient();
  HttpGet getX=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/warX/SharedSessionServlet");
  HttpGet getY=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/warY/SharedSessionServlet");
  String result=runGet(getX,client);
  assertEquals("0",result);
  result=runGet(getX,client);
  assertEquals("1",result);
  result=runGet(getY,client);
  assertEquals("0",result);
  result=runGet(getY,client);
  assertEquals("1",result);
  result=runGet(getX,client);
  assertEquals("2",result);
  HttpClientUtils.closeQuietly(client);
}
