/** 
 * ByteArrayMerger test
 * @throws Exception
 */
@Test public void testByteArrayMerger() throws Exception {
  byte[] arrayOne={1,2};
  byte[] arrayTwo={1,32};
  byte[] result=MergerFactory.getMerger(byte[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(4,result.length);
  byte[] mergedResult={1,2,1,32};
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertEquals(mergedResult[i],result[i]);
  }
}
