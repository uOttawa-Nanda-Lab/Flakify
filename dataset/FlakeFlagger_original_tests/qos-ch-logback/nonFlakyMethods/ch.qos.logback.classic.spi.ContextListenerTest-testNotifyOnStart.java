@Test public void testNotifyOnStart(){
  context.start();
  assertEquals(UpdateType.START,listener.updateType);
  assertEquals(listener.context,context);
}
