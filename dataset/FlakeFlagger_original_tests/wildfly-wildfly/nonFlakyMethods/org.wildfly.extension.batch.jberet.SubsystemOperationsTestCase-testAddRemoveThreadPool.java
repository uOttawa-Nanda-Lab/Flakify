@Test public void testAddRemoveThreadPool() throws Exception {
  final KernelServices kernelServices=boot(getSubsystemXml("/minimal-subsystem.xml"));
  final ModelNode address=createAddress("thread-pool","test-pool");
  final ModelNode addOp=SubsystemOperations.createAddOperation(address);
  addOp.get("max-threads").set(10L);
  final ModelNode keepAlive=addOp.get("keepalive-time");
  keepAlive.get("time").set(100L);
  keepAlive.get("unit").set(TimeUnit.MILLISECONDS.toString());
  executeOperation(kernelServices,addOp);
  final ModelNode removeOp=SubsystemOperations.createRemoveOperation(address);
  executeOperation(kernelServices,removeOp);
  executeOperation(kernelServices,addOp);
  final Operation compositeOp=CompositeOperationBuilder.create().addStep(removeOp).addStep(addOp).build();
  executeOperation(kernelServices,compositeOp);
}
