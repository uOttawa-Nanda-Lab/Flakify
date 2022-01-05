/** 
 * Transaction marked for rollback should not be allowed for commit
 * @throws Throwable
 */
@Test(expected=RollbackException.class) public void testRollbackException() throws Throwable {
  injector.getInstance(OrmTestHelper.class).performTransactionMarkedForRollback();
}
