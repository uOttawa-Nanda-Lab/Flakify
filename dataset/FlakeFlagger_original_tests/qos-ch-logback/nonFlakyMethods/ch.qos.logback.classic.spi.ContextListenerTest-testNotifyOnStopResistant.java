@Test public void testNotifyOnStopResistant(){
  listener.setResetResistant(true);
  context.stop();
  assertEquals(UpdateType.STOP,listener.updateType);
  assertEquals(listener.context,context);
}
