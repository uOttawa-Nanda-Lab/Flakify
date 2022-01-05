@Test public void initializerPopulatesEnvironment(){
  assertThat(this.environment.getProperty("foo"),equalTo("bucket"));
}
