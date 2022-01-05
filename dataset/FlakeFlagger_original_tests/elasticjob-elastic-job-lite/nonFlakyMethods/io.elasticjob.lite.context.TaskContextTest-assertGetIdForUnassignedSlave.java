@Test public void assertGetIdForUnassignedSlave(){
  assertThat(TaskContext.getIdForUnassignedSlave("test_job@-@0@-@READY@-@slave-S0@-@0"),is("test_job@-@0@-@READY@-@unassigned-slave@-@0"));
}
