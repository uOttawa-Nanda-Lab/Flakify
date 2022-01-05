@Test public void testVisitor(){
  String propertyId=PropertyHelper.getPropertyId("category1","foo");
  EqualsPredicate equalsPredicate=new EqualsPredicate<String>(propertyId,"bar");
  TestPredicateVisitor visitor=new TestPredicateVisitor();
  equalsPredicate.accept(visitor);
  Assert.assertSame(equalsPredicate,visitor.visitedComparisonPredicate);
  Assert.assertNull(visitor.visitedArrayPredicate);
  Assert.assertNull(visitor.visitedUnaryPredicate);
  AndPredicate andPredicate=new AndPredicate(equalsPredicate);
  visitor=new TestPredicateVisitor();
  andPredicate.accept(visitor);
  Assert.assertNull(visitor.visitedComparisonPredicate);
  Assert.assertSame(andPredicate,visitor.visitedArrayPredicate);
  Assert.assertNull(visitor.visitedUnaryPredicate);
  NotPredicate notPredicate=new NotPredicate(andPredicate);
  visitor=new TestPredicateVisitor();
  notPredicate.accept(visitor);
  Assert.assertNull(visitor.visitedComparisonPredicate);
  Assert.assertNull(visitor.visitedArrayPredicate);
  Assert.assertSame(notPredicate,visitor.visitedUnaryPredicate);
}
