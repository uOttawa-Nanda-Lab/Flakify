@Test(expected=IllegalStateException.class) public void testIllegalState(){
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.mandatory_file_encoding:FOO");
  this.initializer.onApplicationEvent(this.event);
}
