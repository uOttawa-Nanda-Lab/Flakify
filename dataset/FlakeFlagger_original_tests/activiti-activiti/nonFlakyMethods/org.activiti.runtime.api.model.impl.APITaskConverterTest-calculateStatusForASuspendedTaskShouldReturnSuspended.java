@Test public void calculateStatusForASuspendedTaskShouldReturnSuspended(){
  assertThat(taskConverter.from(taskEntityBuilder().withSuspended(true).build())).isNotNull().extracting(Task::getStatus).containsExactly(SUSPENDED);
}
