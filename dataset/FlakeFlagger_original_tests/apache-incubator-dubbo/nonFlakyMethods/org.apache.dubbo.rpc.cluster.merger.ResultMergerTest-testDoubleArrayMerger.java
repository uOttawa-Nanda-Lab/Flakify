/** 
 * DoubleArrayMerger test
 * @throws Exception
 */
@Test public void testDoubleArrayMerger() throws Exception {
  double[] arrayOne={1.2d,3.5d};
  double[] arrayTwo={2d,34d};
  double[] result=MergerFactory.getMerger(double[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(4,result.length);
  double[] mergedResult={1.2d,3.5d,2d,34d};
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertTrue(mergedResult[i] == result[i]);
  }
}
