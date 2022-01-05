@Test public void isMarshalling(){
  for (  CacheMode mode : EnumSet.allOf(CacheMode.class)) {
    Configuration config=new ConfigurationBuilder().clustering().cacheMode(mode).build();
    CacheProperties configuration=new InfinispanCacheProperties(config);
    if (mode.isDistributed() || mode.isReplicated() || mode.isScattered()) {
      Assert.assertTrue(mode.name(),configuration.isMarshalling());
    }
 else {
      Assert.assertFalse(mode.name(),configuration.isMarshalling());
    }
  }
  Configuration config=new ConfigurationBuilder().clustering().cacheMode(CacheMode.LOCAL).persistence().passivation(false).addSingleFileStore().build();
  Assert.assertTrue(new InfinispanCacheProperties(config).isMarshalling());
  Configuration passivating=new ConfigurationBuilder().read(config).persistence().passivation(true).build();
  Assert.assertTrue(new InfinispanCacheProperties(passivating).isMarshalling());
  Configuration noStore=new ConfigurationBuilder().read(config).persistence().clearStores().build();
  Assert.assertFalse(new InfinispanCacheProperties(noStore).isMarshalling());
}
