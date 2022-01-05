@Test(expected=IllegalArgumentException.class) public void assertBuildWhenCronIsNull(){
  JobCoreConfiguration.newBuilder("test_job",null,3).build();
}
