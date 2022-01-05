@Test public void testNotifyOnReset(){
  context.reset();
  assertEquals(UpdateType.RESET,listener.updateType);
  assertEquals(listener.context,context);
}
