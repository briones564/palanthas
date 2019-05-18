package logicaNegocio;

public class Utilidades {
    
    public static String ajustarString(String string, int dimension) {
    
        StringBuffer stringBuffer = new StringBuffer();
        
        if (string != null){
        
            stringBuffer.append(string);     
        }
        
        stringBuffer.setLength(dimension);
        
        return stringBuffer.toString();
    }
}
