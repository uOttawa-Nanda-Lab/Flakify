@Test public void shouldDecorateRunnable() throws Exception {
  Context.set(context);
  WroTestUtils.runConcurrently(ContextPropagatingCallable.decorate(new Runnable(){
    public void run(){
      assertTrue(Context.isContextSet());
      assertSame(context,Context.get());
    }
  }
),1);
}
