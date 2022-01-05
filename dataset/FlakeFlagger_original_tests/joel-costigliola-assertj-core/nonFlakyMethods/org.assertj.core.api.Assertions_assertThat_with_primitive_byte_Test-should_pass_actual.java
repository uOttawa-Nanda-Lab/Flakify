@Test public void should_pass_actual(){
  AbstractByteAssert<?> assertions=Assertions.assertThat((byte)8);
  assertEquals(new Byte((byte)8),assertions.actual);
}
