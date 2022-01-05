@Test public void removeDysfunctionalSnakes() throws Exception {
  Snake snake=mock(Snake.class);
  willThrow(new IOException()).given(snake).sendMessage(anyString());
  SnakeTimer.addSnake(snake);
  SnakeTimer.broadcast("");
  assertThat(SnakeTimer.getSnakes().size(),is(0));
}
