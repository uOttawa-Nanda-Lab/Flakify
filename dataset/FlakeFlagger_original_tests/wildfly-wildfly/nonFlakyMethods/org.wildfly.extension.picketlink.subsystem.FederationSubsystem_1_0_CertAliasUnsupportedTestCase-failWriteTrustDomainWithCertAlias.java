@Test public void failWriteTrustDomainWithCertAlias() throws Exception {
  String subsystemXml=readResource("federation-subsystem-1.0.xml");
  KernelServices servicesA=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXml(subsystemXml).build();
  PathAddress address=PathAddress.pathAddress(FederationExtension.SUBSYSTEM_PATH).append(ModelElement.FEDERATION.getName(),"federation-without-signatures").append(ModelElement.IDENTITY_PROVIDER.getName(),"idp.war").append(ModelElement.IDENTITY_PROVIDER_TRUST_DOMAIN.getName(),"invalid-trust-domain");
  ModelNode operation=Util.getWriteAttributeOperation(address,ModelElement.IDENTITY_PROVIDER_TRUST_DOMAIN_CERT_ALIAS.getName(),new ModelNode("servercert"));
  servicesA.executeForFailure(operation);
}
