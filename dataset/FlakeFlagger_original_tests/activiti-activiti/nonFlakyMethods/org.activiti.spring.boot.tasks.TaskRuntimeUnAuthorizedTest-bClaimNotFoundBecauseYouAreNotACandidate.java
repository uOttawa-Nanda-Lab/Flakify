@Test(expected=NotFoundException.class) @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void bClaimNotFoundBecauseYouAreNotACandidate(){
  taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(currentTaskId).build());
}
