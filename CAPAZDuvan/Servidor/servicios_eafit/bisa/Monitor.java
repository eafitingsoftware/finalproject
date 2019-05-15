package servicios_eafit.bisa;

import servicios_eafit.ayre.Estudiante;

public class Monitor {

    private String carrera;
    private Estudiante estudiante;

    public Monitor (String carrera, Estudiante estudiante) {

        this.carrera = carrera;
        this.estudiante = estudiante;

    }

    public String getCarrera() {

        return this.carrera;

    }

    public void setCarrera(String carrera) {

        this.carrera = carrera;

    }

    public Estudiante getEstudiante() {

        return this.estudiante;

    }

    public void setEstudiante(Estudiante estudiante) {

        this.estudiante = estudiante;

    }
   
}
