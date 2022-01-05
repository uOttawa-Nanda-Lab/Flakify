@Test public void assertGetAvailableJobInstances(){
  when(jobNodeStorage.getJobNodeChildrenKeys("instances")).thenReturn(Arrays.asList("127.0.0.1@-@0","127.0.0.2@-@0"));
  when(serverService.isEnableServer("127.0.0.1")).thenReturn(true);
  assertThat(instanceService.getAvailableJobInstances(),is(Collections.singletonList(new JobInstance("127.0.0.1@-@0"))));
}
