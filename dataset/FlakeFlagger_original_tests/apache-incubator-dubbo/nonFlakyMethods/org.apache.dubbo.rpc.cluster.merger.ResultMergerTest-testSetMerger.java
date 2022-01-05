/** 
 * SetMerger test
 * @throws Exception
 */
@Test public void testSetMerger() throws Exception {
  Set<Object> set1=new HashSet<Object>();
  set1.add(null);
  set1.add("1");
  set1.add("2");
  Set<Object> set2=new HashSet<Object>();
  set2.add("2");
  set2.add("3");
  Set result=MergerFactory.getMerger(Set.class).merge(set1,set2);
  Assert.assertEquals(4,result.size());
  Assert.assertEquals(new HashSet<String>(){
{
      add(null);
      add("1");
      add("2");
      add("3");
    }
  }
,result);
}
