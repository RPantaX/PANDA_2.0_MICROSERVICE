package aws.lambda.entity;

public class ConductorTable {
    private String id;
    private String DNI;
    private String nombres;
    private String apellidos;
    private String nroLicencia;
    private Integer idUser;
    private Boolean certPsicofisico;
    private Boolean certMecanicaBasica;
    private Boolean certPrimerosAux;
    private Boolean certSeguridadVial;
    private Boolean certBuquesPortuaria;
    private String camionPlaca;
    private String camionMarca;
    private String camionModelo;
    private String camionAnioFabricacion;
    private Boolean camionCertHabVehicular;
    private String camionSOAT;
    private Boolean camionRevTecnica;
    private String camionFechMantenimiento;
    private String carretaPlaca;
    private Double carretaCapacidad;
    private Double carretaCargaUtil;
    private String carretaConfVehicular;
    private Boolean estado;
    private String usuarioModificador;
    private String creadoEn;
    private String modificadoEn;
    private String eliminadoEn;

    public ConductorTable() {
    }

    public ConductorTable( String id, String DNI, String nombres, String apellidos, String nroLicencia, Integer idUser, Boolean certPsicofisico, Boolean certMecanicaBasica, Boolean certPrimerosAux, Boolean certSeguridadVial, Boolean certBuquesPortuaria, String camionPlaca, String camionMarca, String camionModelo, String camionAnioFabricacion, Boolean camionCertHabVehicular, String camionSOAT, Boolean camionRevTecnica, String camionFechMantenimiento, String carretaPlaca, Double carretaCapacidad, Double carretaCargaUtil, String carretaConfVehicular, Boolean estado, String usuarioModificador, String creadoEn, String modificadoEn, String eliminadoEn) {
        this.id=id;
        this.DNI = DNI;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nroLicencia = nroLicencia;
        this.idUser = idUser;
        this.certPsicofisico = certPsicofisico;
        this.certMecanicaBasica = certMecanicaBasica;
        this.certPrimerosAux = certPrimerosAux;
        this.certSeguridadVial = certSeguridadVial;
        this.certBuquesPortuaria = certBuquesPortuaria;
        this.camionPlaca = camionPlaca;
        this.camionMarca = camionMarca;
        this.camionModelo = camionModelo;
        this.camionAnioFabricacion = camionAnioFabricacion;
        this.camionCertHabVehicular = camionCertHabVehicular;
        this.camionSOAT = camionSOAT;
        this.camionRevTecnica = camionRevTecnica;
        this.camionFechMantenimiento = camionFechMantenimiento;
        this.carretaPlaca = carretaPlaca;
        this.carretaCapacidad = carretaCapacidad;
        this.carretaCargaUtil = carretaCargaUtil;
        this.carretaConfVehicular = carretaConfVehicular;
        this.estado = estado;
        this.usuarioModificador = usuarioModificador;
        this.creadoEn = creadoEn;
        this.modificadoEn = modificadoEn;
        this.eliminadoEn = eliminadoEn;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNroLicencia() {
        return nroLicencia;
    }

    public void setNroLicencia(String nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Boolean getCertPsicofisico() {
        return certPsicofisico;
    }

    public void setCertPsicofisico(Boolean certPsicofisico) {
        this.certPsicofisico = certPsicofisico;
    }

    public Boolean getCertMecanicaBasica() {
        return certMecanicaBasica;
    }

    public void setCertMecanicaBasica(Boolean certMecanicaBasica) {
        this.certMecanicaBasica = certMecanicaBasica;
    }

    public Boolean getCertPrimerosAux() {
        return certPrimerosAux;
    }

    public void setCertPrimerosAux(Boolean certPrimerosAux) {
        this.certPrimerosAux = certPrimerosAux;
    }

    public Boolean getCertSeguridadVial() {
        return certSeguridadVial;
    }

    public void setCertSeguridadVial(Boolean certSeguridadVial) {
        this.certSeguridadVial = certSeguridadVial;
    }

    public String getCamionPlaca() {
        return camionPlaca;
    }

    public void setCamionPlaca(String camionPlaca) {
        this.camionPlaca = camionPlaca;
    }

    public String getCamionAnioFabricacion() {
        return camionAnioFabricacion;
    }

    public void setCamionAnioFabricacion(String camionAnioFabricacion) {
        this.camionAnioFabricacion = camionAnioFabricacion;
    }

    public Boolean getCertBuquesPortuaria() {
        return certBuquesPortuaria;
    }

    public void setCertBuquesPortuaria(Boolean certBuquesPortuaria) {
        this.certBuquesPortuaria = certBuquesPortuaria;
    }

    public String getCamionMarca() {
        return camionMarca;
    }

    public void setCamionMarca(String camionMarca) {
        this.camionMarca = camionMarca;
    }

    public String getCamionModelo() {
        return camionModelo;
    }

    public void setCamionModelo(String camionModelo) {
        this.camionModelo = camionModelo;
    }

    public Boolean getCamionCertHabVehicular() {
        return camionCertHabVehicular;
    }

    public void setCamionCertHabVehicular(Boolean camionCertHabVehicular) {
        this.camionCertHabVehicular = camionCertHabVehicular;
    }

    public String getCamionSOAT() {
        return camionSOAT;
    }

    public void setCamionSOAT(String camionSOAT) {
        this.camionSOAT = camionSOAT;
    }

    public String getCamionFechMantenimiento() {
        return camionFechMantenimiento;
    }

    public void setCamionFechMantenimiento(String camionFechMantenimiento) {
        this.camionFechMantenimiento = camionFechMantenimiento;
    }

    public Double getCarretaCapacidad() {
        return carretaCapacidad;
    }

    public void setCarretaCapacidad(Double carretaCapacidad) {
        this.carretaCapacidad = carretaCapacidad;
    }

    public Double getCarretaCargaUtil() {
        return carretaCargaUtil;
    }

    public void setCarretaCargaUtil(Double carretaCargaUtil) {
        this.carretaCargaUtil = carretaCargaUtil;
    }

    public String getCarretaConfVehicular() {
        return carretaConfVehicular;
    }

    public void setCarretaConfVehicular(String carretaConfVehicular) {
        this.carretaConfVehicular = carretaConfVehicular;
    }

    public String getCarretaPlaca() {
        return carretaPlaca;
    }

    public void setCarretaPlaca(String carretaPlaca) {
        this.carretaPlaca = carretaPlaca;
    }

    public Boolean getCamionRevTecnica() {
        return camionRevTecnica;
    }

    public void setCamionRevTecnica(Boolean camionRevTecnica) {
        this.camionRevTecnica = camionRevTecnica;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getUsuarioModificador() {
        return usuarioModificador;
    }

    public void setUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
    }

    public String getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(String creadoEn) {
        this.creadoEn = creadoEn;
    }

    public String getModificadoEn() {
        return modificadoEn;
    }

    public void setModificadoEn(String modificadoEn) {
        this.modificadoEn = modificadoEn;
    }

    public String getEliminadoEn() {
        return eliminadoEn;
    }

    public void setEliminadoEn(String eliminadoEn) {
        this.eliminadoEn = eliminadoEn;
    }
}
