@Test(expected=NotFoundException.class) @WithUserDetails(value="garth",userDetailsServiceBeanName="myUserDetailsService") public void eClaimTaskCreatedForGroup(){
  taskRuntime.delete(TaskPayloadBuilder.delete().withTaskId(currentTaskId).build());
}
