@Test public void assertRemoveLocalInstancePathForReconnectedRegistryCenter(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(instanceService.isLocalJobInstanceExisted()).thenReturn(true);
  shutdownListenerManager.new InstanceShutdownStatusJobListener().dataChanged("/test_job/instances/127.0.0.1@-@0",Type.NODE_REMOVED,"");
  verify(schedulerFacade,times(0)).shutdownInstance();
}
