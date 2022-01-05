@Test(expected=AchillesException.class) public void should_exception_when_map_size_does_not_match() throws Exception {
  HashMap<Integer,String> map=new HashMap<Integer,String>();
  map.put(1,"1");
  Validator.validateSize(map,2,"");
}
