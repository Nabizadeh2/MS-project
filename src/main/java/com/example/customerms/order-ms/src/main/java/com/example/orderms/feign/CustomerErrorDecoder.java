package com.example.orderms.feign;

import com.example.orderms.exception.OrderNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.io.IOException;
import java.io.InputStream;

public class CustomerErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        String  message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            //ObjectMapper mapper = new ObjectMapper();
            message = bodyIs.toString();
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        return switch (response.status()) {
            case 400 -> new BadRequestException(message);
            case 404 -> new OrderNotFoundException(message);
            default -> errorDecoder.decode(methodKey, response);
        };
    }


}
