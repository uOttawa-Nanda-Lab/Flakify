@Test public void should_pass_actual(){
  byte[] actual=emptyArray();
  AbstractByteArrayAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
