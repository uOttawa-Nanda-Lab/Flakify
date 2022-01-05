@SuppressWarnings("unchecked") @Test public void test() throws ClassNotFoundException, IOException {
  Collection<Object> basis=Arrays.<Object>asList(1,2,3,4,5);
  new ExternalizerTester<>(DefaultExternalizer.ARRAY_DEQUE.cast(ArrayDeque.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new ArrayDeque<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.ARRAY_LIST.cast(ArrayList.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new ArrayList<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.CONCURRENT_LINKED_DEQUE.cast(ConcurrentLinkedDeque.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new ConcurrentLinkedDeque<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.CONCURRENT_LINKED_QUEUE.cast(ConcurrentLinkedQueue.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new ConcurrentLinkedQueue<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.HASH_SET.cast(HashSet.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new HashSet<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.LINKED_HASH_SET.cast(LinkedHashSet.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new LinkedHashSet<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.LINKED_LIST.cast(LinkedList.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new LinkedList<>(basis));
  ConcurrentHashMap.KeySetView<Object,Boolean> keySetView=ConcurrentHashMap.newKeySet();
  keySetView.addAll(basis);
  new ExternalizerTester<>(DefaultExternalizer.CONCURRENT_HASH_SET.cast(ConcurrentHashMap.KeySetView.class),CollectionExternalizerTestCase::assertCollectionEquals).test(keySetView);
  new ExternalizerTester<>(DefaultExternalizer.COPY_ON_WRITE_ARRAY_LIST.cast(CopyOnWriteArrayList.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new CopyOnWriteArrayList<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.COPY_ON_WRITE_ARRAY_SET.cast(CopyOnWriteArraySet.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new CopyOnWriteArraySet<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_ENUMERATION.cast(Enumeration.class),Assert::assertSame).test(Collections.emptyEnumeration());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_ITERATOR.cast(Iterator.class),Assert::assertSame).test(Collections.emptyIterator());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_LIST.cast(List.class),Assert::assertSame).test(Collections.emptyList());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_LIST_ITERATOR.cast(ListIterator.class),Assert::assertSame).test(Collections.emptyListIterator());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_NAVIGABLE_SET.cast(NavigableSet.class),Assert::assertSame).test(Collections.emptyNavigableSet());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_SET.cast(Set.class),Assert::assertSame).test(Collections.emptySet());
  new ExternalizerTester<>(DefaultExternalizer.EMPTY_SORTED_SET.cast(SortedSet.class),Assert::assertSame).test(Collections.emptySortedSet());
  new ExternalizerTester<>(DefaultExternalizer.SINGLETON_LIST.cast(List.class),CollectionExternalizerTestCase::assertCollectionEquals).test(Collections.singletonList(1));
  new ExternalizerTester<>(DefaultExternalizer.SINGLETON_SET.cast(Set.class),CollectionExternalizerTestCase::assertCollectionEquals).test(Collections.singleton(1));
  new ExternalizerTester<>(DefaultExternalizer.CONCURRENT_SKIP_LIST_SET.cast(ConcurrentSkipListSet.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new ConcurrentSkipListSet<>(basis));
  new ExternalizerTester<>(DefaultExternalizer.TREE_SET.cast(TreeSet.class),CollectionExternalizerTestCase::assertCollectionEquals).test(new TreeSet<>(basis));
}
