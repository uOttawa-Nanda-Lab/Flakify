/** 
 * Testing app has request dumper handler registered. This test checks that request dump over the Websockets is generated correctly.
 */
@Test @OperateOnDeployment(DEPLOYMENT_WS) public void testReqDumpHandlerOnWebsockets(@ArquillianResource URL url) throws Exception {
  URI wsUri=new URI("ws","",TestSuiteEnvironment.getServerAddress(),TestSuiteEnvironment.getHttpPort(),"/" + DEPLOYMENT_WS + "/websocket/Stuart","","");
  new RequestDumpingHandlerTestImpl.WsRequestDumpingHandlerTestImpl(wsUri,logFilePath,true);
}
