/*
 * Copyright 2026 yuezu1026
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
package com.vat.util;

import org.apache.commons.lang3.StringUtils;

public class DataUtil {

    /**
     * 分割路径
     * 
     * @param path
     * @return 返回分割后的路径
     */
    public static String[] separatePath(String path) {
	if (StringUtils.isBlank(path)) {
	    return null;
	}
	String[] sep = path.split("\\.");
	return new String[] { sep[0], sep[1] };
    }

}