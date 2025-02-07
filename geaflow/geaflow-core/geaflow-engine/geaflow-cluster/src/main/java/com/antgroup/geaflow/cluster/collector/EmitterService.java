/*
 * Copyright 2023 AntGroup CO., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.antgroup.geaflow.cluster.collector;

import com.antgroup.geaflow.cluster.task.service.AbstractTaskService;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmitterService extends AbstractTaskService<IEmitterRequest, EmitterRunner> implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmitterService.class);

    private static final String EMITTER_FORMAT = "gryphon-emitter-%d";

    private int slots;

    public EmitterService(int slots) {
        super(EMITTER_FORMAT);
        this.slots = slots;
    }

    protected EmitterRunner[] buildTaskRunner() {
        Preconditions.checkArgument(slots > 0, "fetcher pool should be larger than 0");
        EmitterRunner[] emitterRunners = new EmitterRunner[slots];
        for (int i = 0; i < slots; i++) {
            EmitterRunner runner = new EmitterRunner();
            emitterRunners[i] = runner;
        }
        return emitterRunners;
    }
}
