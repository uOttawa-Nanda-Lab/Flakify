/** 
 * Testing the classes that implements HeapSize and are a part of 0.20. Some are not tested here for example BlockIndex which is tested in TestHFile since it is a non public class
 * @throws IOException
 */
@SuppressWarnings("unchecked") public void testSizes() throws IOException {
  Class cl=null;
  long expected=0L;
  long actual=0L;
  cl=KeyValue.class;
  expected=ClassSize.estimateBase(cl,false);
  KeyValue kv=new KeyValue();
  actual=kv.heapSize();
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=Put.class;
  expected=ClassSize.estimateBase(cl,false);
  expected+=ClassSize.TREEMAP;
  Put put=new Put(Bytes.toBytes(""));
  actual=put.heapSize();
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=LruBlockCache.class;
  actual=LruBlockCache.CACHE_FIXED_OVERHEAD;
  expected=ClassSize.estimateBase(cl,false);
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=CachedBlock.class;
  actual=CachedBlock.PER_BLOCK_OVERHEAD;
  expected=ClassSize.estimateBase(cl,false);
  expected+=ClassSize.estimateBase(String.class,false);
  expected+=ClassSize.estimateBase(ByteBuffer.class,false);
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    ClassSize.estimateBase(String.class,true);
    ClassSize.estimateBase(ByteBuffer.class,true);
    assertEquals(expected,actual);
  }
  cl=MemStore.class;
  actual=MemStore.FIXED_OVERHEAD;
  expected=ClassSize.estimateBase(cl,false);
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  actual=MemStore.DEEP_OVERHEAD;
  expected=ClassSize.estimateBase(cl,false);
  expected+=ClassSize.estimateBase(ReentrantReadWriteLock.class,false);
  expected+=ClassSize.estimateBase(AtomicLong.class,false);
  expected+=ClassSize.estimateBase(ConcurrentSkipListMap.class,false);
  expected+=ClassSize.estimateBase(ConcurrentSkipListMap.class,false);
  expected+=ClassSize.estimateBase(CopyOnWriteArraySet.class,false);
  expected+=ClassSize.estimateBase(CopyOnWriteArrayList.class,false);
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    ClassSize.estimateBase(ReentrantReadWriteLock.class,true);
    ClassSize.estimateBase(AtomicLong.class,true);
    ClassSize.estimateBase(ConcurrentSkipListMap.class,true);
    ClassSize.estimateBase(CopyOnWriteArraySet.class,true);
    ClassSize.estimateBase(CopyOnWriteArrayList.class,true);
    assertEquals(expected,actual);
  }
  cl=Store.class;
  actual=Store.FIXED_OVERHEAD;
  expected=ClassSize.estimateBase(cl,false);
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
  cl=HRegion.class;
  actual=HRegion.FIXED_OVERHEAD;
  expected=ClassSize.estimateBase(cl,false);
  if (expected != actual) {
    ClassSize.estimateBase(cl,true);
    assertEquals(expected,actual);
  }
}
