@Test public void shouldReturnEmptyStringWhenEmptyContentIsProcessed(){
  Assert.assertEquals(StringUtils.EMPTY,engine.process(""));
}
