@Test public void assertJobPropertiesEnumFromValidValue(){
  assertThat(JobProperties.JobPropertiesEnum.from(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey()),is(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER));
}
