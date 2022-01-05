@Test public void testVerifyStaticWebMethodFails() throws NoSuchMethodException, SecurityException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(BrokenSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verifyWebMethod(SampleWS.class.getMethod("discoverNewLands"));
  assertTrue(sut.failed());
  assertEquals(1,sut.verificationFailures.size());
  assertTrue(sut.verificationFailures.get(0) instanceof WebMethodIsStaticOrFinal);
}
