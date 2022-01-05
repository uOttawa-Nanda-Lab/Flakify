@Test public void should_pass_actual(){
  AbstractShortAssert<?> assertions=Assertions.assertThat((short)8);
  assertEquals(new Short((short)8),assertions.actual);
}
