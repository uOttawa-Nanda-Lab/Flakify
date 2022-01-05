@Test public void testVerifyFails() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(BrokenSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verify();
  assertTrue(sut.failed());
  assertEquals(5,sut.verificationFailures.size());
}
