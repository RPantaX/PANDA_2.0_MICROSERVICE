package com.panda.msguiatransporte.aggregates.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;
}
