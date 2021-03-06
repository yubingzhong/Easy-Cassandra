/*
 * Copyright 2012 Otávio Gonçalves de Santana (otaviojava)
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.easycassandra.util;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileUtil {

    private static final int BUFFER_FILE = 512;

    private FileUtil() {
        // Construtor privado
    }

    public static File byteFromFile(byte[] data, String arquivoDestino) {
        FileOutputStream fos;
        byte[] buf;
        int read;
        ByteArrayInputStream input;
        File arquivo = new File(arquivoDestino);
        try {
            fos = new FileOutputStream(arquivo);
            buf = new byte[BUFFER_FILE];
            input = new ByteArrayInputStream(data);
            while ((read = input.read(buf)) != -1) {
                fos.write(buf, 0, read);
            }
            fos.flush();
            fos.close();
        } catch (Exception exception) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null,
                    exception);
        }
        return arquivo;
    }

    public static byte[] fileToByteArray(File file) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
            inputStream.close();
            byte[] arquivoByte = new byte[bytes.length];
            System.arraycopy(bytes, 0, arquivoByte, 0, bytes.length);
            return arquivoByte;
        } catch (IOException exception) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null,
                    exception);
        }
        return new byte[0];
    }
}
