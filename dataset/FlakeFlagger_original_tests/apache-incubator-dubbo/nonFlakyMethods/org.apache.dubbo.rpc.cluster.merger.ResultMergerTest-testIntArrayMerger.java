/** 
 * IntArrayMerger test
 * @throws Exception
 */
@Test public void testIntArrayMerger() throws Exception {
  int[] arrayOne={1,2};
  int[] arrayTwo={2,34};
  int[] result=MergerFactory.getMerger(int[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(4,result.length);
  double[] mergedResult={1,2,2,34};
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertTrue(mergedResult[i] == result[i]);
  }
}
