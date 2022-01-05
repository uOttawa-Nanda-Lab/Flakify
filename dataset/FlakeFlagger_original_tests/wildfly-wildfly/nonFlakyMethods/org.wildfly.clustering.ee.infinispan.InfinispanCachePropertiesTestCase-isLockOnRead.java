@Test public void isLockOnRead(){
  Configuration config=new ConfigurationBuilder().transaction().transactionMode(TransactionMode.TRANSACTIONAL).lockingMode(LockingMode.PESSIMISTIC).locking().isolationLevel(IsolationLevel.REPEATABLE_READ).build();
  Assert.assertTrue(new InfinispanCacheProperties(config).isLockOnRead());
  Configuration optimistic=config=new ConfigurationBuilder().read(config).transaction().lockingMode(LockingMode.OPTIMISTIC).build();
  Assert.assertFalse(new InfinispanCacheProperties(optimistic).isLockOnRead());
  Configuration nonTx=new ConfigurationBuilder().read(config).transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL).build();
  Assert.assertFalse(new InfinispanCacheProperties(nonTx).isLockOnRead());
  Configuration readCommitted=config=new ConfigurationBuilder().read(config).locking().isolationLevel(IsolationLevel.READ_COMMITTED).build();
  Assert.assertFalse(new InfinispanCacheProperties(readCommitted).isLockOnRead());
}
