/** 
 * Testing app has request dumper handler registered. This test checks that request dump over the HTTPS is generated correctly.
 */
@Test @OperateOnDeployment(DEPLOYMENT_DUMP) public void testReqDumpHandlerOnHttps(@ArquillianResource URL url) throws Exception {
  URL httpsUrl=new URL("https://" + url.getHost() + ":"+ HTTPS_PORT+ url.getPath()+ "file.txt");
  new RequestDumpingHandlerTestImpl.HttpsRequestDumpingHandlerTestImpl(httpsUrl.toURI(),logFilePath,true);
}
