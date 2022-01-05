@SuppressWarnings("unchecked") @Test public void test() throws ClassNotFoundException, IOException {
  Map<Object,Object> basis=Stream.of(1,2,3,4,5).collect(Collectors.<Integer,Object,Object>toMap(i -> i,i -> Integer.toString(i)));
  new ExternalizerTester<>(DefaultExternalizer.CONCURRENT_HASH_MAP.cast(ConcurrentHashMap.class),MapExternalizerTestCase::assertMapEquals).test(new ConcurrentHashMap<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.HASH_MAP.cast(HashMap.class),MapExternalizerTestCase::assertMapEquals).test(new HashMap<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.LINKED_HASH_MAP.cast(LinkedHashMap.class),MapExternalizerTestCase::assertMapEquals).test(new LinkedHashMap<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_MAP.cast(Map.class),Assert::assertSame).test(Collections.emptyMap());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_NAVIGABLE_MAP.cast(NavigableMap.class),Assert::assertSame).test(Collections.emptyNavigableMap());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_SORTED_MAP.cast(SortedMap.class),Assert::assertSame).test(Collections.emptySortedMap());
  new ExternalizerTester<>(DefaultExternalizer.SINGLETON_MAP.cast(Map.class),MapExternalizerTestCase::assertMapEquals).test(Collections.singletonMap(1,2));
  new ExternalizerTester<>(DefaultExternalizer.CONCURRENT_SKIP_LIST_MAP.cast(ConcurrentSkipListMap.class),MapExternalizerTestCase::assertMapEquals).test(new ConcurrentSkipListMap<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.TREE_MAP.cast(TreeMap.class),MapExternalizerTestCase::assertMapEquals).test(new TreeMap<>(basis));
}
