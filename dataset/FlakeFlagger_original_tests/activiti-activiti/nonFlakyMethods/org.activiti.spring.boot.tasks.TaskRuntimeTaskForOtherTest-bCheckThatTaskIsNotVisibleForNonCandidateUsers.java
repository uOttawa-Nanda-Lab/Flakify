@Test @WithUserDetails(value="salaboy",userDetailsServiceBeanName="myUserDetailsService") public void bCheckThatTaskIsNotVisibleForNonCandidateUsers(){
  Page<Task> tasks=taskRuntime.tasks(Pageable.of(0,50));
  assertThat(tasks.getContent()).hasSize(0);
}
