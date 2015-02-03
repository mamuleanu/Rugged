/* Copyright 2013-2015 CS Systèmes d'Information
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
package org.orekit.rugged.errors;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.orekit.rugged.raster.Tile;

/**
 * Class managing debug dumps.
 * <p>
 * <em>WARNING</em>: this class is public only for technical reasons,
 * it is not considered to belong to the public API of the library and should
 * not be called by user code. It is only intended to be called internally by
 * the Rugged library itself. This class may be changed or even removed at any
 * time, so user code should not rely on it.
 * </p>
 * @author Luc Maisonobe
 */
public class DumpManager {

    /** Dump file (default initial value is null, i.e. nothing is dumped). */
    private static final ThreadLocal<Dump> DUMP = new ThreadLocal<Dump>();

    /** Private constructor for utility class.
     */
    private DumpManager() {
        // by default, nothing is dumped
    }

    /** Activate debug dump.
     * @param file dump file
     * @exception RuggedException if debug dump is already active for this thread
     * or if debug file cannot be opened
     */
    public static void activate(final File file) throws RuggedException {
        if (isActive()) {
            throw new RuggedException(RuggedMessages.DEBUG_DUMP_ALREADY_ACTIVE);
        } else {
            try {
                DUMP.set(new Dump(new PrintWriter(file)));
            } catch (IOException ioe) {
                throw new RuggedException(ioe, RuggedMessages.DEBUG_DUMP_ACTIVATION_ERROR,
                                          file.getAbsolutePath(), ioe.getLocalizedMessage());
            }
        }
    }

    /** Deactivate debug dump.
     * @exception RuggedException if debug dump is already active for this thread
     */
    public static void deactivate() throws RuggedException {
        if (isActive()) {
            DUMP.get().deactivate();
            DUMP.set(null);
        } else {
            throw new RuggedException(RuggedMessages.DEBUG_DUMP_ALREADY_ACTIVE);
        }
    }

    /** Check if dump is active for this thread.
     * @return true if dump is active for this thread
     */
    public static boolean isActive() {
        return DUMP.get() != null;
    }

    /** Dump DEM cell data.
     * @param tile tile to which the cell belongs
     * @param latitudeIndex latitude index of the cell
     * @param longitudeIndex longitude index of the cell
     * @param elevation elevation of the cell
     */
    public static void dumpTileCell(final Tile tile,
                                    final int latitudeIndex, final int longitudeIndex,
                                    final double elevation) {
        if (isActive()) {
            DUMP.get().dumpTileCell(tile, latitudeIndex, longitudeIndex, elevation);
        }
    }

}
