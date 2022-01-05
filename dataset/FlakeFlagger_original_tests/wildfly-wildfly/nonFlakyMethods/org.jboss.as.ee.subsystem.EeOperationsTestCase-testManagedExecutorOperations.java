@Test public void testManagedExecutorOperations() throws Exception {
  final KernelServices kernelServices=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(getSubsystemXml()).build();
  final ModelNode address=Operations.createAddress(ClientConstants.SUBSYSTEM,EeExtension.SUBSYSTEM_NAME,"managed-executor-service","default");
  ModelNode op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"queue-length",Integer.MAX_VALUE)).addStep(Operations.createWriteAttributeOperation(address,"core-threads",5)).build().getOperation();
  executeForSuccess(kernelServices,op);
  op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"max-threads",5)).addStep(Operations.createWriteAttributeOperation(address,"queue-length",10)).addStep(Operations.createWriteAttributeOperation(address,"core-threads",0)).build().getOperation();
  executeForSuccess(kernelServices,op);
  op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"core-threads",4)).addStep(Operations.createWriteAttributeOperation(address,"max-threads",4)).build().getOperation();
  executeForSuccess(kernelServices,op);
}
