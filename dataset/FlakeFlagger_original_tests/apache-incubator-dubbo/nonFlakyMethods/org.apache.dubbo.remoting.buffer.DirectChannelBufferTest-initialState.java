@Test public void initialState(){
  assertEquals(CAPACITY,buffer.capacity());
  assertEquals(0,buffer.readerIndex());
}
