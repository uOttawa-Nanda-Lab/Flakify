/** 
 * Asserts that bindings may be added through composite ops.
 * @throws Exception
 */
@Test public void testCompositeBindingOps() throws Exception {
  final KernelServices services=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(getSubsystemXml()).build();
  final ModelNode addr=Operations.createAddress(ModelDescriptionConstants.SUBSYSTEM,NamingExtension.SUBSYSTEM_NAME,NamingSubsystemModel.BINDING,"java:global/alookup");
  final ModelNode addOp=Operations.createAddOperation(addr);
  addOp.get(NamingSubsystemModel.BINDING_TYPE).set(NamingSubsystemModel.LOOKUP);
  final ModelNode compositeOp=Operations.CompositeOperationBuilder.create().addStep(addOp).addStep(Operations.createWriteAttributeOperation(addr,NamingSubsystemModel.LOOKUP,"java:global/a")).build().getOperation();
  ModelTestUtils.checkOutcome(services.executeOperation(compositeOp));
}
