@Test public void should_pass_actual(){
  AbstractDoubleAssert<?> assertions=Assertions.assertThat(8d);
  assertEquals(new Double(8d),assertions.actual);
}
