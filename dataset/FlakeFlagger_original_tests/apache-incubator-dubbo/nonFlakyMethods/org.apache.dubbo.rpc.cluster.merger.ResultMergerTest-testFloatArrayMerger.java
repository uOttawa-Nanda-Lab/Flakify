/** 
 * FloatArrayMerger test
 * @throws Exception
 */
@Test public void testFloatArrayMerger() throws Exception {
  float[] arrayOne={1.2f,3.5f};
  float[] arrayTwo={2f,34f};
  float[] result=MergerFactory.getMerger(float[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(4,result.length);
  double[] mergedResult={1.2f,3.5f,2f,34f};
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertTrue(mergedResult[i] == result[i]);
  }
}
