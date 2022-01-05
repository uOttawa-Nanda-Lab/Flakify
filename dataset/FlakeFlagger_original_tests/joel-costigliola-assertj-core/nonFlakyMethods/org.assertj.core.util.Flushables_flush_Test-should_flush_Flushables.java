@Test public void should_flush_Flushables(){
  FlushableStub[] toFlush=new FlushableStub[]{new FlushableStub(),new FlushableStub()};
  Flushables.flush(toFlush);
  assertFlushed(toFlush);
}
