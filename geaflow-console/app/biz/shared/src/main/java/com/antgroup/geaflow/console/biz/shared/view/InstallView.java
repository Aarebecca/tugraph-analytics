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

package com.antgroup.geaflow.console.biz.shared.view;

import com.antgroup.geaflow.console.common.util.type.GeaflowDeployMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstallView extends IdView {

    private PluginConfigView runtimeClusterConfig;

    private PluginConfigView runtimeMetaConfig;

    private PluginConfigView haMetaConfig;

    private PluginConfigView metricConfig;

    private PluginConfigView remoteFileConfig;

    private PluginConfigView dataConfig;

    private GeaflowDeployMode deployMode;

}