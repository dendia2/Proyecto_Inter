package db;

import clases.Eliminatoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que interactúa con la tabla Eliminatoria.
 * @author Denys (3D)
 * @see clases.Eliminatoria
 */
public class Eliminatoria_DB {
    /**
     * Inserta una fila.
     * @param con conector
     * @param eliminatoria la fila a insertar
     * @throws Exception error de sql
     */
    public void inserta(Connection con, Eliminatoria eliminatoria) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into eliminatoria" +
                    "(nom_comp) values (?)");
            stmt.setString(1, eliminatoria.getNombre());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al insertar eliminatoria: " + ex.getMessage());
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * Carga todas las filas.
     * @param con conector
     * @return lista de String
     * @throws Exception error de sql
     */
    public List<String> cargarNombres(Connection con) throws Exception {
        List<String> nombres = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "select * from eliminatoria");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String s = rs.getString("nom_comp");
                nombres.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Ha habido un problema al cargar eliminatorias: " +
                    ex.getMessage());
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        }
        return nombres;
    }
}
