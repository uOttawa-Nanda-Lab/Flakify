/** 
 * ListMerger test
 * @throws Exception
 */
@Test public void testListMerger() throws Exception {
  List<Object> list1=new ArrayList<Object>(){
{
      add(null);
      add("1");
      add("2");
    }
  }
;
  List<Object> list2=new ArrayList<Object>(){
{
      add("3");
      add("4");
    }
  }
;
  List result=MergerFactory.getMerger(List.class).merge(list1,list2);
  Assert.assertEquals(5,result.size());
  ArrayList<String> expected=new ArrayList<String>(){
{
      add(null);
      add("1");
      add("2");
      add("3");
      add("4");
    }
  }
;
  Assert.assertEquals(expected,result);
}
