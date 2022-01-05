/** 
 * ShortArrayMerger test
 * @throws Exception
 */
@Test public void testShortArrayMerger() throws Exception {
  short[] arrayOne={1,2};
  short[] arrayTwo={2,34};
  short[] result=MergerFactory.getMerger(short[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(4,result.length);
  double[] mergedResult={1,2,2,34};
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertTrue(mergedResult[i] == result[i]);
  }
}
