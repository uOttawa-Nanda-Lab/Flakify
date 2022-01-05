@Test public void should_pass_actual(){
  AbstractIntegerAssert<?> assertions=Assertions.assertThat(8);
  assertEquals(new Integer(8),assertions.actual);
}
