@Test public void calculateStatusCreatedAndNotAssignedTaskShouldReturnCreated(){
  assertThat(taskConverter.from(taskBuilder().build())).isNotNull().extracting(Task::getStatus).containsExactly(CREATED);
}
