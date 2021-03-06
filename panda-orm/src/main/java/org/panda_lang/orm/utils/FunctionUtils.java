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

package org.panda_lang.orm.utils;

import java.util.function.Supplier;

public final class FunctionUtils {

    private FunctionUtils() { }

    /**
     * Convert value into supplier
     *
     * @param value the value to return
     * @param <T> type of value
     * @return supplier returning the value
     */
    public static <T> Supplier<T> toSupplier(T value) {
        return () -> value;
    }

}
