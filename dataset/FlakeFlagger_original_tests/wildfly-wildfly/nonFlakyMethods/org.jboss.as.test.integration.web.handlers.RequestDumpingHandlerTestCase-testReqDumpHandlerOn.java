/** 
 * Testing app has already defined request dumper handler. This test checks that when a request to URL is performed then request detail data is stored in proper format in the proper log file.
 */
@Test @OperateOnDeployment(DEPLOYMENT_DUMP) public void testReqDumpHandlerOn(@ArquillianResource URL url) throws Exception {
  new RequestDumpingHandlerTestImpl.HttpRequestDumpingHandlerTestImpl(url.toURI(),logFilePath,true);
}
