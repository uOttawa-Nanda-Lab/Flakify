@Test public void isTransactional(){
  Configuration config=new ConfigurationBuilder().transaction().transactionMode(TransactionMode.TRANSACTIONAL).build();
  Assert.assertTrue(new InfinispanCacheProperties(config).isTransactional());
  config=new ConfigurationBuilder().transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL).build();
  Assert.assertFalse(new InfinispanCacheProperties(config).isTransactional());
}
