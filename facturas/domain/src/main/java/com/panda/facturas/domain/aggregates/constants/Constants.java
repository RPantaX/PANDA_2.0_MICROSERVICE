package com.panda.facturas.domain.aggregates.constants;

import java.math.BigDecimal;

public class Constants {
    public static final Boolean STATUS_ACTIVE=true;
    public static final Boolean STATUS_INACTIVE=false;
    public static final BigDecimal IGV = BigDecimal.valueOf(0.18);
    public static final String NUMERO_PAG_POR_DEFECTO="0";
    public static final String MEDIDA_PAG_POR_DEFECTO="10";
    public static final String ORDENAR_POR_DEFECTO_FACTURA="fechaEmision";
    public static final String ORDENAR_POR_DEFECTO_EMISOR="creadoEn";
    public static final String ORDENAR_DIRECC_POR_DEFECTO="0";
}
