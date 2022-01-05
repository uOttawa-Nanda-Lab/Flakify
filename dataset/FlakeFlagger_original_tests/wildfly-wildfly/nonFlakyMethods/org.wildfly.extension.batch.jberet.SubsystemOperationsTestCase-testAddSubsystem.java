@Test public void testAddSubsystem() throws Exception {
  final KernelServices kernelServices=boot(null);
  final CompositeOperationBuilder operationBuilder=CompositeOperationBuilder.create();
  final ModelNode subsystemAddress=createAddress(null);
  final ModelNode subsystemAddOp=SubsystemOperations.createAddOperation(subsystemAddress);
  subsystemAddOp.get("default-job-repository").set("in-memory");
  subsystemAddOp.get("default-thread-pool").set("batch");
  operationBuilder.addStep(subsystemAddOp);
  operationBuilder.addStep(SubsystemOperations.createAddOperation(createAddress(InMemoryJobRepositoryDefinition.NAME,"in-memory")));
  final ModelNode threadPool=SubsystemOperations.createAddOperation(createAddress("thread-pool","batch"));
  threadPool.get("max-threads").set(10);
  final ModelNode keepAlive=threadPool.get("keepalive-time");
  keepAlive.get("time").set(100L);
  keepAlive.get("unit").set(TimeUnit.MILLISECONDS.toString());
  operationBuilder.addStep(threadPool);
  executeOperation(kernelServices,operationBuilder.build());
}
