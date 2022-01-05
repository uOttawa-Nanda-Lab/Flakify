/** 
 * Testing app has no request dumper handler registered. This test checks that there is not dumped additional info about executed request in the log file.
 */
@Test @OperateOnDeployment(DEPLOYMENT) public void testReqDumpHandlerOff(@ArquillianResource URL url) throws Exception {
  new RequestDumpingHandlerTestImpl.HttpRequestDumpingHandlerTestImpl(url.toURI(),logFilePath,false);
}
