@Test public void should_pass_actual(){
  AbstractLongAssert<?> assertions=Assertions.assertThat(8L);
  assertEquals(new Long(8),assertions.actual);
}
