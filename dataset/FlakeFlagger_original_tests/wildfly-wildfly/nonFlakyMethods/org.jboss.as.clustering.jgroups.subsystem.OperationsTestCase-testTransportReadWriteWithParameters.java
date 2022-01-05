@Test public void testTransportReadWriteWithParameters() throws Exception {
  KernelServices services=this.buildKernelServices();
  Assert.assertTrue("Could not create services",services.isSuccessfulBoot());
  ModelNode result=services.executeOperation(getProtocolStackAddOperationWithParameters("maximal2"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportWriteOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK,"new-rack"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportReadOperation("maximal","TCP",TransportResourceDefinition.Attribute.RACK));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("new-rack",result.get(RESULT).asString());
}
