@Test public void testVerifyFinalWebMethodFails() throws NoSuchMethodException, SecurityException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(BrokenSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verifyWebMethod(SampleWS.class.getMethod("triggerReport"));
  assertTrue(sut.failed());
  assertEquals(1,sut.verificationFailures.size());
  assertTrue(sut.verificationFailures.get(0) instanceof WebMethodIsStaticOrFinal);
}
