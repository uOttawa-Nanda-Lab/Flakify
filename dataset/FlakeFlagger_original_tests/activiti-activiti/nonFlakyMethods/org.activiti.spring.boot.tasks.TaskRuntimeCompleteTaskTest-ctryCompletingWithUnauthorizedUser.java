@Test(expected=NotFoundException.class) @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void ctryCompletingWithUnauthorizedUser(){
  taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(currentTaskId).build());
}
