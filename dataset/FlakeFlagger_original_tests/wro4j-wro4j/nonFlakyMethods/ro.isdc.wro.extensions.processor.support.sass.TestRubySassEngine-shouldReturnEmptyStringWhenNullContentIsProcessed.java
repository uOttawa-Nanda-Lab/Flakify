@Test public void shouldReturnEmptyStringWhenNullContentIsProcessed(){
  Assert.assertEquals(StringUtils.EMPTY,engine.process(null));
}
