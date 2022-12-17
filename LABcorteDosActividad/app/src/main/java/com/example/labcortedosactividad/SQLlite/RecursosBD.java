package com.example.labcortedosactividad.SQLlite;

public class RecursosBD {
    private static final String TABLA_PUESTOS = "bd_puestos";
    private static final String CAMP_ID = "";
    private static final String CAMP_DEPARTAMENTO = "departamento";
    private static final String CAMP_MUNICIPIO = "municipio";
    private static final String CAMP_SEDE = "sede";
    private static final String CAMP_DIRECCION = "direccion";
    private static final String CAMP_TELEFONO = "telefono";
    private static final String CAMP_EMAIL = "email";
    private static final String CAMP_NAJU_NOMBRE = "naju_nombre";
    private static final String CAMP_FECHA_CORTE = "fecha_corte_reps";

    private static final String crearBDSQLlite = "CREATE TABLE " + TABLA_PUESTOS +" ("+CAMP_DEPARTAMENTO+" TEXT,"
            +CAMP_MUNICIPIO+" TEXT, "+CAMP_SEDE+" TEXT, "+CAMP_DIRECCION+" TEXT, "+CAMP_TELEFONO+" TEXT, " +
            CAMP_EMAIL+" TEXT, "+CAMP_NAJU_NOMBRE+" TEXT, "+CAMP_FECHA_CORTE+" TEXT)";

    public static String getTablaPuestos() {
        return TABLA_PUESTOS;
    }

    public static String getCampId() {
        return CAMP_ID;
    }

    public static String getCampDepartamento() {
        return CAMP_DEPARTAMENTO;
    }

    public static String getCampMunicipio() {
        return CAMP_MUNICIPIO;
    }

    public static String getCampSede() {
        return CAMP_SEDE;
    }

    public static String getCampDireccion() {
        return CAMP_DIRECCION;
    }

    public static String getCampTelefono() {
        return CAMP_TELEFONO;
    }

    public static String getCampEmail() {
        return CAMP_EMAIL;
    }

    public static String getCampNajuNombre() {
        return CAMP_NAJU_NOMBRE;
    }

    public static String getCampFechaCorte() {
        return CAMP_FECHA_CORTE;
    }

    public static String getCrearBDSQLlite() {
        return crearBDSQLlite;
    }
}
