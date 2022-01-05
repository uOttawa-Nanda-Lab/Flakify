@Test(expected=IllegalArgumentException.class) public void assertBuildWhenJobNameIsNull(){
  JobCoreConfiguration.newBuilder(null,"0/1 * * * * ?",3).build();
}
