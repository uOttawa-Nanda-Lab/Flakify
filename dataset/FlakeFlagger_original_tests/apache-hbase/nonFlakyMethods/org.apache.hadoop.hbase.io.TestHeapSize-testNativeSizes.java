/** 
 * Test our hard-coded sizing of native java objects
 */
@SuppressWarnings("unchecked") public void testNativeSizes() throws IOException {
  Class cl=null;
  long expected=0L;
  long actual=0L;
  cl=ArrayList.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.ARRAYLIST;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=ByteBuffer.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.BYTE_BUFFER;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=Integer.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.INTEGER;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=Object.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.OBJECT;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=TreeMap.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.TREEMAP;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=String.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.STRING;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=ConcurrentHashMap.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.CONCURRENT_HASHMAP;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=ConcurrentSkipListMap.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.CONCURRENT_SKIPLISTMAP;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=ReentrantReadWriteLock.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.REENTRANT_LOCK;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=AtomicLong.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.ATOMIC_LONG;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=AtomicInteger.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.ATOMIC_INTEGER;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=AtomicBoolean.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.ATOMIC_BOOLEAN;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=CopyOnWriteArraySet.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.COPYONWRITE_ARRAYSET;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=CopyOnWriteArrayList.class;
  expected=ClassSize.estimateBase(cl,false);
  actual=ClassSize.COPYONWRITE_ARRAYLIST;
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
}
