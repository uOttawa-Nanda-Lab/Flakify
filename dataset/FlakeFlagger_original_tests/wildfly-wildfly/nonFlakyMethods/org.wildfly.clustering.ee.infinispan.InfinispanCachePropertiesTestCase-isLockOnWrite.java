@Test public void isLockOnWrite(){
  Configuration config=new ConfigurationBuilder().transaction().transactionMode(TransactionMode.TRANSACTIONAL).lockingMode(LockingMode.PESSIMISTIC).build();
  Assert.assertTrue(new InfinispanCacheProperties(config).isLockOnWrite());
  Configuration optimistic=config=new ConfigurationBuilder().read(config).transaction().lockingMode(LockingMode.OPTIMISTIC).build();
  Assert.assertFalse(new InfinispanCacheProperties(optimistic).isLockOnWrite());
  Configuration nonTx=new ConfigurationBuilder().read(config).transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL).build();
  Assert.assertFalse(new InfinispanCacheProperties(nonTx).isLockOnWrite());
}
