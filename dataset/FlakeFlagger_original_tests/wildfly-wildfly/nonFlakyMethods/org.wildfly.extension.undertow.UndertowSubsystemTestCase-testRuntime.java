@Test public void testRuntime() throws Exception {
  setProperty();
  KernelServicesBuilder builder=createKernelServicesBuilder(RUNTIME).setSubsystemXml(getSubsystemXml());
  KernelServices mainServices=builder.build();
  testRuntime(mainServices,virtualHostName,flag);
  testRuntimeOther(mainServices);
  testRuntimeLast(mainServices);
}
