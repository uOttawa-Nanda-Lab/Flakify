@Test public void isPersistent(){
  for (  CacheMode mode : EnumSet.allOf(CacheMode.class)) {
    Configuration config=new ConfigurationBuilder().clustering().cacheMode(mode).build();
    CacheProperties configuration=new InfinispanCacheProperties(config);
    if (mode.isDistributed() || mode.isReplicated() || mode.isScattered()) {
      Assert.assertTrue(mode.name(),configuration.isPersistent());
    }
 else {
      Assert.assertFalse(mode.name(),configuration.isPersistent());
    }
  }
  Configuration config=new ConfigurationBuilder().clustering().cacheMode(CacheMode.LOCAL).persistence().passivation(false).addSingleFileStore().build();
  Assert.assertTrue(new InfinispanCacheProperties(config).isPersistent());
  Configuration passivating=new ConfigurationBuilder().read(config).persistence().passivation(true).build();
  Assert.assertFalse(new InfinispanCacheProperties(passivating).isPersistent());
  Configuration noStore=new ConfigurationBuilder().read(config).persistence().clearStores().build();
  Assert.assertFalse(new InfinispanCacheProperties(noStore).isPersistent());
}
