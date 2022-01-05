@Test public void should_pass_actual(){
  AbstractBooleanAssert<?> assertions=Assertions.assertThat(true);
  assertEquals(true,assertions.actual);
}
