package daneker.code.weathersensor.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();


        for(FieldError error : errors){
            sb.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage() == null?error.getCode() : error.getDefaultMessage())
                    .append("; ");
        }

        throw new MeasurementException(sb.toString());
    }
}
