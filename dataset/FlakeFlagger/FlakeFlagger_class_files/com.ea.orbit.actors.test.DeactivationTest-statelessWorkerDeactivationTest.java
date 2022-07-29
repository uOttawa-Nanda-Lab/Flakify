/*
Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1.  Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
2.  Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
    its contributors may be used to endorse or promote products derived
    from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.ea.orbit.actors.test;


import com.ea.orbit.actors.OrbitStage;
import com.ea.orbit.actors.test.actors.ISomeActor;
import com.ea.orbit.actors.test.actors.IStatelessThing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeactivationTest extends ClientTest
{

    @Test public void statelessWorkerDeactivationTest() throws ExecutionException,InterruptedException,TimeoutException{OrbitStage stage1=createStage();OrbitStage client=createClient();IStatelessThing actor5=client.getReference(IStatelessThing.class,"1000");final Set<UUID> set1=new HashSet<>();{final List<Future<UUID>> futures=new ArrayList<>();for (int i=0;i < 100;i++){futures.add(actor5.getUniqueActivationId(5000));}for (Future<UUID> f:futures){set1.add(f.get(10,TimeUnit.SECONDS));}}assertTrue(set1.size() > 1);assertTrue(set1.size() <= 100);clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(8));UUID theSurviving=actor5.getUniqueActivationId().get();clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(8));stage1.cleanup(true);final Set<UUID> set2=new HashSet<>();{final List<Future<UUID>> futures=new ArrayList<>();for (int i=0;i < 25;i++){futures.add(actor5.getUniqueActivationId(1000));}for (Future<UUID> f:futures){set2.add(f.get(10,TimeUnit.SECONDS));}}assertTrue(set2.size() > 1);assertTrue(set2.size() <= 100);assertTrue(set2.contains(theSurviving));set2.retainAll(set1);assertEquals(1,set2.size());}
}
