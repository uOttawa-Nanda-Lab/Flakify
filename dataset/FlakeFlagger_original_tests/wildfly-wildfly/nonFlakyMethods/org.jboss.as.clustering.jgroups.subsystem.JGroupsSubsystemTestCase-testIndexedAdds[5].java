/** 
 * Tests that the 'fork' and 'stack' resources allow indexed adds for the 'protocol' children. This is important for the work being done for WFCORE-401. This work involves calculating the operations to bring the slave domain model into sync with the master domain model. Without ordered resources, that would mean on reconnect if the master had added a protocol somewhere in the middle, the protocol would get added to the end rather at the correct place.
 */
@Test public void testIndexedAdds() throws Exception {
  if (!this.schema.since(JGroupsSchema.VERSION_3_0))   return;
  final KernelServices services=this.buildKernelServices();
  ModelNode originalSubsystemModel=services.readWholeModel().get(JGroupsSubsystemResourceDefinition.PATH.getKeyValuePair());
  ModelNode originalChannelModel=originalSubsystemModel.get(ChannelResourceDefinition.pathElement("ee").getKeyValuePair());
  ModelNode originalForkModel=originalChannelModel.get(ForkResourceDefinition.pathElement("web").getKeyValuePair());
  Assert.assertTrue(originalForkModel.isDefined());
  originalForkModel.protect();
  Assert.assertTrue(0 < originalForkModel.get(ProtocolResourceDefinition.WILDCARD_PATH.getKey()).keys().size());
  ModelNode originalStackModel=originalSubsystemModel.get(StackResourceDefinition.pathElement("maximal").getKeyValuePair());
  Assert.assertTrue(originalStackModel.isDefined());
  originalStackModel.protect();
  final PathAddress subsystemAddress=PathAddress.pathAddress(JGroupsSubsystemResourceDefinition.PATH);
  final PathAddress forkAddress=subsystemAddress.append(ChannelResourceDefinition.pathElement("ee")).append(ForkResourceDefinition.pathElement("web"));
  final PathAddress stackAddress=subsystemAddress.append(StackResourceDefinition.pathElement("maximal"));
  ModelNode add=Operations.createAddOperation(forkAddress.append(ProtocolResourceDefinition.pathElement("MERGE3")),0);
  ModelTestUtils.checkOutcome(services.executeOperation(add));
  ModelNode subsystemModel=services.readWholeModel().get(JGroupsSubsystemResourceDefinition.PATH.getKeyValuePair());
  ModelNode channelModel=subsystemModel.get(ChannelResourceDefinition.pathElement("ee").getKeyValuePair());
  ModelNode forkModel=channelModel.get(ForkResourceDefinition.pathElement("web").getKeyValuePair());
  Assert.assertEquals(originalForkModel.keys().size() + 1,forkModel.get(ProtocolResourceDefinition.WILDCARD_PATH.getKey()).keys().size());
  Assert.assertEquals("MERGE3",forkModel.get(ProtocolResourceDefinition.WILDCARD_PATH.getKey()).keys().iterator().next());
  ModelNode remove=Util.createRemoveOperation(stackAddress.append(ProtocolResourceDefinition.pathElement("FD")));
  ModelTestUtils.checkOutcome(services.executeOperation(remove));
  add=Operations.createAddOperation(stackAddress.append(ProtocolResourceDefinition.pathElement("FD")),3);
  ModelTestUtils.checkOutcome(services.executeOperation(add));
  subsystemModel=services.readWholeModel().get(JGroupsSubsystemResourceDefinition.PATH.getKeyValuePair());
  ModelNode stackModel=subsystemModel.get(StackResourceDefinition.pathElement("maximal").getKeyValuePair());
  Assert.assertEquals(originalStackModel,stackModel);
}
