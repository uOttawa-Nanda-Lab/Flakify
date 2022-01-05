@Test public void testVerifyAnnotatedSampleWSWithExclusionSucceeds() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(AnnotatedSampleWSImplWithExclusion.class,null,deploymentReflectionIndex);
  sut.verify();
  assertFalse(sut.failed());
}
