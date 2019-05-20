package logicaNegocio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Utilidades {
    
    public static String ajustarString(String string, int dimension) {
    
        StringBuffer stringBuffer = new StringBuffer();
        
        if (string != null){
        
            stringBuffer.append(string);     
        }
        
        stringBuffer.setLength(dimension);
        
        return stringBuffer.toString();
    }
    
    public static String leerString (RandomAccessFile randomAccessFile, int longitud) throws IOException {
    
        char[] chars = new char[longitud];
        
        for (int i = 0; i < longitud; i++) {
        
            chars[i] = randomAccessFile.readChar();
        }     
        return new String(chars);
    }
}
