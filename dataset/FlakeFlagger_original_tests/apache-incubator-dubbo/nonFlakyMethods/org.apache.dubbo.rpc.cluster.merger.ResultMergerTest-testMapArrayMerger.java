/** 
 * LongArrayMerger test
 * @throws Exception
 */
@Test public void testMapArrayMerger() throws Exception {
  Map<Object,Object> mapOne=new HashMap(){
{
      put("11",222);
      put("223",11);
    }
  }
;
  Map<Object,Object> mapTwo=new HashMap(){
{
      put("3333",3232);
      put("444",2323);
    }
  }
;
  Map<Object,Object> result=MergerFactory.getMerger(Map.class).merge(mapOne,mapTwo);
  Assert.assertEquals(4,result.size());
  Map<Object,Object> mergedResult=new HashMap(){
{
      put("11",222);
      put("223",11);
      put("3333",3232);
      put("444",2323);
    }
  }
;
  Assert.assertEquals(mergedResult,result);
}
