@Test public void testNotifyOnStopNotResistant(){
  context.stop();
  assertEquals(UpdateType.RESET,listener.updateType);
  assertEquals(listener.context,context);
}
