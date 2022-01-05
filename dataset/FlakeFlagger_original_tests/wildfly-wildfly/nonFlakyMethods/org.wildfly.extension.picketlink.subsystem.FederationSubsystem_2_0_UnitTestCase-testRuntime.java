@Test public void testRuntime() throws Exception {
  System.setProperty("jboss.server.data.dir",System.getProperty("java.io.tmpdir"));
  System.setProperty("jboss.home.dir",System.getProperty("java.io.tmpdir"));
  System.setProperty("jboss.server.server.dir",System.getProperty("java.io.tmpdir"));
  KernelServicesBuilder builder=createKernelServicesBuilder(new AdditionalInitialization(){
    @Override protected RunningMode getRunningMode(){
      return RunningMode.NORMAL;
    }
    @Override protected void setupController(    ControllerInitializer controllerInitializer){
      super.setupController(controllerInitializer);
      controllerInitializer.addPath("jboss.server.data.dir",System.getProperty("java.io.tmpdir"),null);
    }
  }
).setSubsystemXml(getSubsystemXml());
  KernelServices mainServices=builder.build();
  assertTrue(mainServices.isSuccessfulBoot());
}
