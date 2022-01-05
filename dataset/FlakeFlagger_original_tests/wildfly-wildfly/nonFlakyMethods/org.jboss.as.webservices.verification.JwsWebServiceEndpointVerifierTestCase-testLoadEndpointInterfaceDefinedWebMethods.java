@Test public void testLoadEndpointInterfaceDefinedWebMethods() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(GoodSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  Collection<Method> endpointInterfaceMethods=sut.endpointInterfaceDefinedWebMethods();
  Set<String> methodNames=new HashSet<>();
  for (  Method endpointInterfaceMethod : endpointInterfaceMethods)   methodNames.add(endpointInterfaceMethod.getName());
  assertTrue(methodNames.contains("performWork"));
  assertTrue(methodNames.contains("discoverNewLands"));
  assertTrue(methodNames.contains("isWorking"));
  assertTrue(methodNames.contains("triggerReport"));
}
