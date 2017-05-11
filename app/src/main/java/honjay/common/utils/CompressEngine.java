package honjay.common.utils;

import android.app.Activity;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honjaychen.dagger.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/**
 * Created by honjayChen on 2017/4/11.
 */

public class CompressEngine {

    public static String GzipCompresstoBase64(String string) throws IOException {
        try {
              byte[] compressbyte = Base64.decode(string, Base64.DEFAULT);
              ByteArrayOutputStream os = new ByteArrayOutputStream();
              GZIPOutputStream gos = new GZIPOutputStream(os);
              gos.write(compressbyte);
              gos.close();
              byte[] compressed = os.toByteArray();
              os.close();
              return Base64.encodeToString(compressed, Base64.DEFAULT);
        } catch(IOException e){
               throw new RuntimeException(e);
        }
    }

    public static String GzipBase64toDecompress(String string) throws IOException {
        byte[] decompressbyte = Base64.decode(string, Base64.DEFAULT);
        try (ByteArrayInputStream bin = new ByteArrayInputStream(decompressbyte);
             GZIPInputStream gzipper = new GZIPInputStream(bin))
        {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int len;
            while ((len = gzipper.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            gzipper.close();
            out.close();
            return   new String(out.toByteArray(), StandardCharsets.UTF_8);
                    //Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
     }

    public static String DeflaterCompresstoBase64(String string) throws IOException {
        try {
            byte[] compressbyte = Base64.decode(string, Base64.DEFAULT);

            Deflater deflater = new Deflater();
            deflater.setInput(compressbyte);
            deflater.finish();

            byte[] bytesCompressed = new byte[Short.MAX_VALUE];

            int numberOfBytesAfterCompression = deflater.deflate(bytesCompressed);

            byte[] returnValues = new byte[numberOfBytesAfterCompression];

            System.arraycopy
                    (
                            bytesCompressed,
                            0,
                            returnValues,
                            0,
                            numberOfBytesAfterCompression
                    );
            return Base64.encodeToString(returnValues, Base64.DEFAULT);
        } catch(Exception e){
               throw new RuntimeException(e);
        }
    }

    public static String DeflaterBase64toDecompress(String string) throws IOException {
        try {
              byte[] decompressbyte = Base64.decode(string, Base64.DEFAULT);
              byte[] output = new byte[0];

              Inflater decompresser = new Inflater();
              decompresser.reset();
              decompresser.setInput(decompressbyte);

              ByteArrayOutputStream o = new ByteArrayOutputStream(decompressbyte.length);
              try {
                  byte[] buf = new byte[1024];
                  while (!decompresser.finished()) {
                    int i = decompresser.inflate(buf);
                    o.write(buf, 0, i);
                  }
                  output = o.toByteArray();
              } catch (Exception e) {
                  output = decompressbyte;
                  e.printStackTrace();
              } finally {
                  try {
                      o.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              decompresser.end();
              return  new String(output, StandardCharsets.UTF_8);
                      //Base64.encodeToString(output, Base64.DEFAULT);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}