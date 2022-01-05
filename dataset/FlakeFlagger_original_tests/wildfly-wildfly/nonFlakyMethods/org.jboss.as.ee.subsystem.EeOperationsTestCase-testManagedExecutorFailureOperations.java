@Test public void testManagedExecutorFailureOperations() throws Exception {
  final KernelServices kernelServices=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(getSubsystemXml()).build();
  final ModelNode address=Operations.createAddress(ClientConstants.SUBSYSTEM,EeExtension.SUBSYSTEM_NAME,"managed-executor-service","default");
  ModelNode op=Operations.createWriteAttributeOperation(address,"core-threads",0);
  ModelNode result=kernelServices.executeOperation(op);
  Assert.assertFalse(Operations.isSuccessfulOutcome(result));
  op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"queue-length",Integer.MAX_VALUE)).addStep(Operations.createWriteAttributeOperation(address,"core-threads",0)).build().getOperation();
  result=kernelServices.executeOperation(op);
  Assert.assertFalse(Operations.isSuccessfulOutcome(result));
  op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"queue-length",0)).addStep(Operations.createWriteAttributeOperation(address,"core-threads",0)).build().getOperation();
  result=kernelServices.executeOperation(op);
  Assert.assertFalse(Operations.isSuccessfulOutcome(result));
  op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"core-threads",4)).addStep(Operations.createWriteAttributeOperation(address,"max-threads",1)).build().getOperation();
  result=kernelServices.executeOperation(op);
  Assert.assertFalse(Operations.isSuccessfulOutcome(result));
  op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"queue-length","${test.queue-length:10}")).addStep(Operations.createWriteAttributeOperation(address,"core-threads","${test.core-threads:500}")).build().getOperation();
  result=kernelServices.executeOperation(op);
  Assert.assertFalse(Operations.isSuccessfulOutcome(result));
  final int calculatedMaxThreads=(ProcessorInfo.availableProcessors() * 2);
  op=CompositeOperationBuilder.create().addStep(Operations.createWriteAttributeOperation(address,"core-threads",calculatedMaxThreads)).addStep(Operations.createWriteAttributeOperation(address,"max-threads",calculatedMaxThreads - 1)).build().getOperation();
  result=kernelServices.executeOperation(op);
  Assert.assertFalse(Operations.isSuccessfulOutcome(result));
}
