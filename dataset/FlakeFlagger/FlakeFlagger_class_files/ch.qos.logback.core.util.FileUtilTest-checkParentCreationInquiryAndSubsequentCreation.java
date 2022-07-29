/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.core.util;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileUtilTest {

  Context context = new ContextBase();
  FileUtil fileUtil = new FileUtil(context);
  List<File> cleanupList = new ArrayList<File>();
  // test-output folder is not always clean
  int diff =  new Random().nextInt(10000);

  @Before
  public void setUp() throws Exception {
    
  }

  @Test public void checkParentCreationInquiryAndSubsequentCreation(){File file=new File(CoreTestConstants.OUTPUT_DIR_PREFIX + "/fu" + diff + "/testing.txt");cleanupList.add(file);cleanupList.add(file.getParentFile());assertTrue(FileUtil.isParentDirectoryCreationRequired(file));assertTrue(FileUtil.createMissingParentDirectories(file));assertFalse(FileUtil.isParentDirectoryCreationRequired(file));}


}
