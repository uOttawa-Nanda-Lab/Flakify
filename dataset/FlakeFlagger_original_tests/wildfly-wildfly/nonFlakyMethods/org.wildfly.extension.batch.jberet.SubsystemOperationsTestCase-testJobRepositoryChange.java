@Test public void testJobRepositoryChange() throws Exception {
  final KernelServices kernelServices=boot();
  final CompositeOperationBuilder compositeOp=CompositeOperationBuilder.create();
  final ModelNode address=createAddress(InMemoryJobRepositoryDefinition.NAME,"new-job-repo");
  compositeOp.addStep(SubsystemOperations.createAddOperation(address));
  compositeOp.addStep(SubsystemOperations.createWriteAttributeOperation(createAddress(null),"default-thread-pool","new-job-repo"));
  executeOperation(kernelServices,compositeOp.build());
}
