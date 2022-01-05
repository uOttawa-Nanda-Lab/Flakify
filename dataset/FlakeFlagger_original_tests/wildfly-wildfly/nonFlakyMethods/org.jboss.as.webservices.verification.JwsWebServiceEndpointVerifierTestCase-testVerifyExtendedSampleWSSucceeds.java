@Test public void testVerifyExtendedSampleWSSucceeds() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(ConcreteSampleWSImpl.class,null,deploymentReflectionIndex);
  sut.verify();
  assertFalse(sut.failed());
}
