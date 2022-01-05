@Test public void testLegacyOperations() throws Exception {
  List<ModelNode> ops=new LinkedList<>();
  PathAddress subsystemAddress=PathAddress.pathAddress(JGroupsSubsystemResourceDefinition.PATH);
  PathAddress udpAddress=subsystemAddress.append(StackResourceDefinition.pathElement("udp"));
  ModelNode op=Util.createAddOperation(subsystemAddress);
  op.get("default-stack").set("udp");
  ops.add(op);
  op=Util.createAddOperation(udpAddress);
  ModelNode transport=new ModelNode();
  transport.get("type").set("UDP");
  transport.get("socket-binding").set("jgroups-udp");
  ModelNode protocols=new ModelNode();
  String[] protocolList={"PING","MERGE3","FD_SOCK","FD","VERIFY_SUSPECT","BARRIER","pbcast.NAKACK2","UNICAST3","pbcast.STABLE","pbcast.GMS","UFC","MFC","FRAG2","RSVP"};
  for (int i=0; i < protocolList.length; i++) {
    ModelNode protocol=new ModelNode();
    protocol.get("type").set(protocolList[i]);
    protocols.add(protocol);
  }
  op.get("transport").set(transport);
  op.get("protocols").set(protocols);
  ops.add(op);
  KernelServices servicesA=createKernelServicesBuilder(createAdditionalInitialization()).setBootOperations(ops).build();
  Assert.assertTrue("Subsystem boot failed!",servicesA.isSuccessfulBoot());
  final ModelNode modelA=servicesA.readWholeModel();
  validateModel(modelA);
  servicesA.shutdown();
  final ModelNode operation=createDescribeOperation();
  final ModelNode result=servicesA.executeOperation(operation);
  Assert.assertTrue("the subsystem describe operation has to generate a list of operations to recreate the subsystem",!result.hasDefined(ModelDescriptionConstants.FAILURE_DESCRIPTION));
  final List<ModelNode> operations=result.get(ModelDescriptionConstants.RESULT).asList();
  servicesA.shutdown();
  final KernelServices servicesC=createKernelServicesBuilder(createAdditionalInitialization()).setBootOperations(operations).build();
  final ModelNode modelC=servicesC.readWholeModel();
  compare(modelA,modelC);
  assertRemoveSubsystemResources(servicesC,getIgnoredChildResourcesForRemovalTest());
}
