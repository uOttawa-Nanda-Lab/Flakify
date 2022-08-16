/*
 * Copyright (C) 2012 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * These authors would like to acknowledge the Spanish Ministry of Industry,
 * Tourism and Trade, for the support in the project TSI020301-2008-2
 * "PIRAmIDE: Personalizable Interactions with Resources on AmI-enabled
 * Mobile Dynamic Environments", led by Treelogic
 * ( http://www.treelogic.com/ ):
 *
 *   http://www.piramidepse.com/
 */

package com.google.zxing.oned.rss.expanded;

import java.util.List;

import com.google.zxing.oned.OneDReader;
import org.junit.Assert;
import org.junit.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;

public final class RSSExpandedStackedInternalTestCase extends Assert {
	
	@Test public void testCompleteDecode() throws Exception{OneDReader rssExpandedReader=new RSSExpandedReader();BinaryBitmap binaryMap=TestCaseUtil.getBinaryBitmap("test/data/blackbox/rssexpandedstacked-2/1000.png");Result result=rssExpandedReader.decode(binaryMap);assertEquals("(01)98898765432106(3202)012345(15)991231",result.getText());}
	
	
}
