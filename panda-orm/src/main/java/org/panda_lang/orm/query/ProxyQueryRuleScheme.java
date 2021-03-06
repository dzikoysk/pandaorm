/*
 * Copyright (c) 2019-2020 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.orm.query;

import org.panda_lang.utilities.commons.collection.Pair;

import java.util.ArrayList;
import java.util.List;

final class ProxyQueryRuleScheme implements DataQueryRuleScheme {

    private final List<Pair<? extends DataRuleProperty, Integer>> properties;

    ProxyQueryRuleScheme(List<Pair<? extends DataRuleProperty, Integer>> properties) {
        this.properties = properties;
    }

    @Override
    public ProxyQueryRule toRule(Object[] values) {
        List<Pair<DataRuleProperty, Object>> mappedProperties = new ArrayList<>();

        for (Pair<? extends DataRuleProperty, Integer> property : properties) {
            mappedProperties.add(new Pair<>(property.getKey(), values[property.getValue()]));
        }

        return new ProxyQueryRule(mappedProperties);
    }

}