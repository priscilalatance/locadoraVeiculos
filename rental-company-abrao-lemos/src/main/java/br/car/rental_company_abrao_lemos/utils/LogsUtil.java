package br.car.rental_company_abrao_lemos.utils;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogsUtil {
    
    public static StructuredArgument propertiesPlaca(String placa){
        return StructuredArguments.keyValue(Consts.PLACA, placa);
        
    }
    
    public static StructuredArgument jsonRequest(Object request){
            return StructuredArguments.keyValue(Consts.REQUEST, request);
    }
    
    public static Marker information(){
            return MarkerFactory.getMarker(Consts.INFORMATION);
    }
    
    public static Marker errorIntern(){
        return MarkerFactory.getMarker(Consts.ERROR_INTERN_SYSTEM);
    }
    
}
