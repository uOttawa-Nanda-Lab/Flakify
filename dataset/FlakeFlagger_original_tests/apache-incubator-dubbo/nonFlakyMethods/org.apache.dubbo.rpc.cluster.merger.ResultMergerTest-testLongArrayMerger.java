/** 
 * LongArrayMerger test
 * @throws Exception
 */
@Test public void testLongArrayMerger() throws Exception {
  long[] arrayOne={1l,2l};
  long[] arrayTwo={2l,34l};
  long[] result=MergerFactory.getMerger(long[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(4,result.length);
  double[] mergedResult={1l,2l,2l,34l};
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertTrue(mergedResult[i] == result[i]);
  }
}
