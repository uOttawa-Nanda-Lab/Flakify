/** 
 * Tests access to transport attributes
 */
@Test public void testTransportReadWriteOperation() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode result=services.executeOperation(getTransportReadOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("rack1",ExpressionResolver.TEST_RESOLVER.resolveExpressions(result.get(RESULT)).asString());
  result=services.executeOperation(getTransportWriteOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK,"new-rack"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportReadOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("new-rack",result.get(RESULT).asString());
}
