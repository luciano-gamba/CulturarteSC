package Logica;

import DataTypes.EnumRetorno;
import DataTypes.DataAporte;
import DataTypes.DataPropuesta;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "nickname")
public class Colaborador extends Usuario {

    @OneToMany(mappedBy = "miColaborador")
    private List<Aporte> misAportes;

    public Colaborador() {
        //this.misAportes = new ArrayList<>();
    }

    public Colaborador(String nickname, String email, String nombre, String apellido, LocalDate fecNac, String imagen, String contraseña, String imagenWeb) {
        super(nickname, email, nombre, apellido, fecNac, imagen, contraseña, imagenWeb);
        this.misAportes = new ArrayList<>();
    }

    public Aporte createAporte(String titulo, double aporte, int cantidad, EnumRetorno retorno) {

        if (!misAportes.isEmpty()) {
            for (Aporte mio : misAportes) {
                if (titulo.equals(mio.getTituloMiPropuesta())) {
                    return null;
                }
            }
        }
        Aporte a = new Aporte(this, aporte, cantidad, retorno);
        //misAportes.add(a);
        return a;
    }

    public Aporte createAporte(String titulo, double aporte, int cantidad, EnumRetorno retorno, LocalDateTime fecAp) {

        if (!misAportes.isEmpty()) {
            for (Aporte mio : misAportes) {
                if (titulo.equals(mio.getTituloMiPropuesta())) {
                    return null;
                }
            }
        }
        Aporte a = new Aporte(this, aporte, cantidad, retorno, fecAp);
        //misAportes.add(a);
        return a;
    }

    public void añadirAporte(Aporte a) {
        misAportes.add(a);
    }

    public List<DataPropuesta> getPropuestas() {
        List<DataPropuesta> listaPropuestasColas = new ArrayList<>();
        for (Aporte a : misAportes) {
            DataPropuesta DP = a.getPropuesta();
            if (DP != null) {
                listaPropuestasColas.add(a.getPropuesta());
            }
        }
        return listaPropuestasColas;
    }

    public List<String> getTituloPropuestas() {
        List<String> listaPropuestas = new ArrayList<>();
        for (Aporte a : this.misAportes) {
            listaPropuestas.add(a.getTituloNickMiPropuesta());
        }
        return listaPropuestas;
    }

    public DataAporte getDataAporte(String tituloNick) {
        for (Aporte a : misAportes) {
            if (tituloNick.equals(a.getTituloNickMiPropuesta())) {
                return new DataAporte(a.getAporte(), a.getFechaHora(), a.getCantidad(), a.getRetorno(), a.getNicknameMiColaborador(), a.getTituloMiPropuesta(), a.getImagenMiPropuesta(), a.getNecesaria());
            }
        }
        return null;
    }

    public Aporte getAporte(String tituloNick) {
        for (Aporte a : misAportes) {
            if (tituloNick.equals(a.getTituloNickMiPropuesta())) {
                return a;
            }
        }
        return null;
    }
    
    public Aporte getAporteXtitulo(String titulo) {
        for (Aporte a : misAportes) {
            if (titulo.equals(a.getTituloMiPropuesta())) {
                return a;
            }
        }
        return null;
    }

    public Aporte borrarAporte(String tituloNick) {
        for (Aporte a : misAportes) {
            if (tituloNick.equals(a.getTituloNickMiPropuesta())) {
                a.desvincular();
                misAportes.remove(a);
                return a;
            }
        }
        return null;
    }

    public List<DataAporte> getListaAportes() {
        List<DataAporte> listaAportes = new ArrayList<>();
        for (Aporte a : misAportes) {
            DataAporte aporteActual = new DataAporte(a.getAporte(), a.getFechaHora(), a.getTituloMiPropuesta());
            listaAportes.add(aporteActual);
        }
        return listaAportes;
    }

   

}
