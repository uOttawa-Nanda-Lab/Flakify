@Test public void testRuntime() throws Exception {
  setProperty();
  KernelServicesBuilder builder=createKernelServicesBuilder(UndertowSubsystemTestCase.RUNTIME).setSubsystemXml(getSubsystemXml());
  KernelServices mainServices=builder.build();
  testRuntime(mainServices,virtualHostName,flag);
}
