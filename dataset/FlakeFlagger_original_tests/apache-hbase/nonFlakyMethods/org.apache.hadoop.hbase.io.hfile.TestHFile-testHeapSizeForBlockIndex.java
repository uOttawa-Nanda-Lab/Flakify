/** 
 * Checks if the HeapSize calculator is within reason
 */
@SuppressWarnings("unchecked") public void testHeapSizeForBlockIndex() throws IOException {
  Class cl=null;
  long expected=0L;
  long actual=0L;
  cl=BlockIndex.class;
  expected=ClassSize.estimateBase(cl,false);
  BlockIndex bi=new BlockIndex(Bytes.BYTES_RAWCOMPARATOR);
  actual=bi.heapSize();
  expected-=ClassSize.align(3 * ClassSize.ARRAY);
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
}
