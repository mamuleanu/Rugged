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
package org.orekit.rugged.core.dem;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.orekit.bodies.GeodeticPoint;
import org.orekit.rugged.api.GroundPoint;
import org.orekit.rugged.api.TileUpdater;

/** Interface for Digital Elevation Model algorithm.
 * @author Luc Maisonobe
 */
public interface IntersectionAlgorithm {

    /** Set up the tiles management.
     * @param updater updater used to load Digital Elevation Model tiles
     * @param maxCachedTiles maximum number of tiles stored in the cache
     */
    void setUpTilesManagement(TileUpdater updater, int maxCachedTiles);

    /** Compute intersection of line with Digital Elevation Model.
     * @param latitude latitude of line arrival at zero altitude
     * @param longitude longitude of line arrival at zero altitude
     * @param direction arrival direction in (East, North, Zenith) frame
     * (the direction is from spacecraft to ground)
     * @return point at which the line first enters ground
     */
    GroundPoint intersection(double latitude0, double longitude0, Vector3D direction);

}