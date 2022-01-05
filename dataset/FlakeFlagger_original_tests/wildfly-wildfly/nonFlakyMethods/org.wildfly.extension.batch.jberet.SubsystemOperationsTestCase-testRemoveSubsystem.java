@Test public void testRemoveSubsystem() throws Exception {
  final KernelServices kernelServices=boot();
  final ModelNode removeSubsystemOp=SubsystemOperations.createRemoveOperation(createAddress(null));
  executeOperation(kernelServices,removeSubsystemOp);
}
