@Test public void calculateStatusForAnAssignedTaskShouldReturnAssigned(){
  assertThat(taskConverter.from(taskBuilder().withAssignee("testUser").build())).isNotNull().extracting(Task::getStatus).containsExactly(ASSIGNED);
}
