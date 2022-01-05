@Test public void should_pass_actual(){
  AbstractFloatAssert<?> assertions=Assertions.assertThat(8f);
  assertEquals(new Float(8),assertions.actual);
}
