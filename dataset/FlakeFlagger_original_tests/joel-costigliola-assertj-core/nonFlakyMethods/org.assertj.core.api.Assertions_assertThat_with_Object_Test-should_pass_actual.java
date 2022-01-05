@Test public void should_pass_actual(){
  Object actual=new Object();
  AbstractObjectAssert<?,Object> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
