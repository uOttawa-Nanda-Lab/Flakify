@Test public void calculateStatusForACancelledTaskShouldReturnCancelled(){
  assertThat(taskConverter.from(taskEntityBuilder().withCancelled(true).build())).isNotNull().extracting(Task::getStatus).containsExactly(CANCELLED);
}
