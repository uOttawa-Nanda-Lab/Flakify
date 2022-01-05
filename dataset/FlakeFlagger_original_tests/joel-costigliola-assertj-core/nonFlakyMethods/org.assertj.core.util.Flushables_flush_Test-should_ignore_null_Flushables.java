@Test public void should_ignore_null_Flushables(){
  FlushableStub c=new FlushableStub();
  FlushableStub[] toFlush=new FlushableStub[]{null,c};
  Flushables.flush(toFlush);
  assertFlushed(c);
}
