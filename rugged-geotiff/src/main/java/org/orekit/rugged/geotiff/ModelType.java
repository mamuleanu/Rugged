/* Copyright 2013-2014 CS Systèmes d'Information
 * Licensed to CS Systèmes d'Information (CS) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * CS licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.orekit.rugged.geotiff;


enum ModelType {

    UNDEFINED(0),
    PROJECTED(1),
    GEOGRAPHIC(2),
    GEOCENTRIC(3);

    /** Type ID. */
    private final int id;

    /** Simple constructor.
     * @param id key id
     */
    private ModelType(final int id) {
        this.id   = id;
    }

    /** Get the type corresponding to an id.
     * @param id type id
     * @return the type corresponding to the id
     * @exception IllegalArgumentException if the id does not correspond to a known type
     */
    public static ModelType getType(final int id) {
        for (ModelType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

}
