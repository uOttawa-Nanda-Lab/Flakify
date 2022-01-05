@Test public void should_ignore_thrown_errors(){
  FlushableStub[] toFlush=new FlushableStub[]{new FlushableStub(new IOException("")),new FlushableStub()};
  Flushables.flush(toFlush);
  assertFlushed(toFlush);
}
