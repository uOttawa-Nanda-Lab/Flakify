@Test public void testThreadPoolChange() throws Exception {
  final KernelServices kernelServices=boot();
  final CompositeOperationBuilder compositeOp=CompositeOperationBuilder.create();
  final ModelNode address=createAddress("thread-pool","test-pool");
  final ModelNode addOp=SubsystemOperations.createAddOperation(address);
  addOp.get("max-threads").set(10L);
  final ModelNode keepAlive=addOp.get("keepalive-time");
  keepAlive.get("time").set(100L);
  keepAlive.get("unit").set(TimeUnit.MILLISECONDS.toString());
  compositeOp.addStep(addOp);
  compositeOp.addStep(SubsystemOperations.createWriteAttributeOperation(createAddress(null),"default-thread-pool","test-pool"));
  executeOperation(kernelServices,compositeOp.build());
}
