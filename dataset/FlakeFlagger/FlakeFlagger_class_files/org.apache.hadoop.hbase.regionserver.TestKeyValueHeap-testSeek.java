/*
 * Copyright 2009 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hbase.regionserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;


public class TestKeyValueHeap extends HBaseTestCase
implements HConstants {
  private static final boolean PRINT = false;

  List<Scanner> scanners = new ArrayList<Scanner>();

  private byte [] row1;
  private byte [] fam1;
  private byte [] col1;
  private byte [] data;

  private byte [] row2;
  private byte [] fam2;
  private byte [] col2;

  private byte [] col3;
  private byte [] col4;
  private byte [] col5;

  public void testSeek(){List<KeyValue> l1=new ArrayList<KeyValue>();l1.add(new KeyValue(row1,fam1,col5,data));l1.add(new KeyValue(row2,fam1,col1,data));l1.add(new KeyValue(row2,fam1,col2,data));scanners.add(new Scanner(l1));List<KeyValue> l2=new ArrayList<KeyValue>();l2.add(new KeyValue(row1,fam1,col1,data));l2.add(new KeyValue(row1,fam1,col2,data));scanners.add(new Scanner(l2));List<KeyValue> l3=new ArrayList<KeyValue>();l3.add(new KeyValue(row1,fam1,col3,data));l3.add(new KeyValue(row1,fam1,col4,data));l3.add(new KeyValue(row1,fam2,col1,data));l3.add(new KeyValue(row1,fam2,col2,data));l3.add(new KeyValue(row2,fam1,col3,data));scanners.add(new Scanner(l3));List<KeyValue> expected=new ArrayList<KeyValue>();expected.add(new KeyValue(row2,fam1,col1,data));KeyValueHeap kvh=new KeyValueHeap(scanners.toArray(new Scanner[0]),KeyValue.COMPARATOR);KeyValue seekKv=new KeyValue(row2,fam1,null,null);kvh.seek(seekKv);List<KeyValue> actual=new ArrayList<KeyValue>();actual.add(kvh.peek());assertEquals(expected.size(),actual.size());for (int i=0;i < expected.size();i++){assertEquals(expected.get(i),actual.get(i));if (PRINT){System.out.println("expected " + expected.get(i) + "\nactual   " + actual.get(i) + "\n");}}}

  private static class Scanner implements KeyValueScanner {
    private Iterator<KeyValue> iter;
    private KeyValue current;
    private boolean closed = false;

    public Scanner(List<KeyValue> list) {
      Collections.sort(list, KeyValue.COMPARATOR);
      iter = list.iterator();
      if(iter.hasNext()){
        current = iter.next();
      }
    }

    public KeyValue peek() {
      return current;
    }

    public KeyValue next() {
      KeyValue oldCurrent = current;
      if(iter.hasNext()){
        current = iter.next();
      } else {
        current = null;
      }
      return oldCurrent;
    }

    public void close(){
      closed = true;
    }

    public boolean isClosed() {
      return closed;
    }

    public boolean seek(KeyValue seekKv) {
      while(iter.hasNext()){
        KeyValue next = iter.next();
        int ret = KeyValue.COMPARATOR.compare(next, seekKv);
        if(ret >= 0){
          current = next;
          return true;
        }
      }
      return false;
    }
  }

}
