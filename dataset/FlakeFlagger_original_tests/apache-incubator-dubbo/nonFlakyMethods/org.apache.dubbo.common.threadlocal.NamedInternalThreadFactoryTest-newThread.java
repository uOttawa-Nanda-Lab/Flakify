@Test public void newThread() throws Exception {
  NamedInternalThreadFactory namedInternalThreadFactory=new NamedInternalThreadFactory();
  Thread t=namedInternalThreadFactory.newThread(new Runnable(){
    @Override public void run(){
    }
  }
);
  Assert.assertTrue("thread is not InternalThread",t.getClass().equals(InternalThread.class));
}
