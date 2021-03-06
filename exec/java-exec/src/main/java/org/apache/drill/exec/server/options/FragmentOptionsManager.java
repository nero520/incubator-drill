/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.drill.exec.server.options;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eigenbase.sql.SqlLiteral;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class FragmentOptionsManager implements OptionManager{
  static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FragmentOptionsManager.class);

  ImmutableMap<String, OptionValue> options;
  OptionManager systemOptions;

  public FragmentOptionsManager(OptionManager systemOptions, OptionList options){
    Map<String, OptionValue> tmp = Maps.newHashMap();
    for(OptionValue v : options){
      tmp.put(v.name, v);
    }
    this.options = ImmutableMap.copyOf(tmp);
    this.systemOptions = systemOptions;
  }

  @Override
  public Iterator<OptionValue> iterator() {
    return Iterables.concat(systemOptions, options.values()).iterator();
  }

  @Override
  public OptionValue getOption(String name) {
    OptionValue value = options.get(name);
    if (value == null) {
      value = systemOptions.getOption(name);
    }
    return value;
  }

  @Override
  public void setOption(OptionValue value) throws SetOptionException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setOption(String name, SqlLiteral literal) throws SetOptionException {
    throw new UnsupportedOperationException();
  }

  @Override
  public OptionAdmin getAdmin() {
    throw new UnsupportedOperationException();
  }

  @Override
  public OptionManager getSystemManager() {
    throw new UnsupportedOperationException();
  }

  @Override
  public OptionList getSessionOptionList() {
    throw new UnsupportedOperationException();
  }

}
