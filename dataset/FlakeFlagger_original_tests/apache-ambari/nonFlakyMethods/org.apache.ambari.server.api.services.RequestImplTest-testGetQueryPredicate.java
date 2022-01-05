@Test public void testGetQueryPredicate(){
  String uri="http://foo.bar.com/api/v1/clusters?foo=bar&orProp1=5|orProp2!=6|orProp3<100&prop!=5&prop2>10&prop3>=20&prop4<500&prop5<=1&fields=field1,category/field2";
  Request request=new TestRequest(null,null,null,Request.Type.GET,null,uri);
  Predicate predicate=request.getQueryPredicate();
  Set<BasePredicate> setPredicates=new HashSet<BasePredicate>();
  setPredicates.add(new EqualsPredicate<String>("foo","bar"));
  Set<BasePredicate> setOrPredicates=new HashSet<BasePredicate>();
  setOrPredicates.add(new EqualsPredicate<String>("orProp1","5"));
  setOrPredicates.add(new NotPredicate(new EqualsPredicate<String>("orProp2","6")));
  setOrPredicates.add(new LessPredicate<String>("orProp3","100"));
  setPredicates.add(new OrPredicate(setOrPredicates.toArray(new BasePredicate[3])));
  setPredicates.add(new NotPredicate(new EqualsPredicate<String>("prop","5")));
  setPredicates.add(new GreaterPredicate<String>("prop2","10"));
  setPredicates.add(new GreaterEqualsPredicate<String>("prop3","20"));
  setPredicates.add(new LessPredicate<String>("prop4","500"));
  setPredicates.add(new LessEqualsPredicate<String>("prop5","1"));
  Predicate expectedPredicate=new AndPredicate(setPredicates.toArray(new BasePredicate[6]));
  assertEquals(expectedPredicate,predicate);
}
