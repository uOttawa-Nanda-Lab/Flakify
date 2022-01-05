@Test public void testProtocolReadWriteOperation() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode result=services.executeOperation(getProtocolStackAddOperationWithParameters("maximal2"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getProtocolReadOperation("maximal","MPING",SocketBindingProtocolResourceDefinition.Attribute.SOCKET_BINDING));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("jgroups-mping",result.get(RESULT).asString());
  result=services.executeOperation(getProtocolWriteOperation("maximal","MPING",SocketBindingProtocolResourceDefinition.Attribute.SOCKET_BINDING,"new-socket-binding"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getProtocolReadOperation("maximal","MPING",SocketBindingProtocolResourceDefinition.Attribute.SOCKET_BINDING));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("new-socket-binding",result.get(RESULT).asString());
}
