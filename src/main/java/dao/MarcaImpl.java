package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Marca;

public class MarcaImpl extends Conexion implements ICRUD<Marca> {

    @Override
    public void registrar(Marca mar) throws Exception {
        String sql = "insert into Marca"
                + "(NOMMAR,ESTMAR) values (?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, mar.getNOMMAR());
            ps.setString(2, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en registrar Marca" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Marca mar) throws Exception {
                String sql = "update Marca set"
                + " NOMMAR=?, ESTMAR=? where IDMAR=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, mar.getNOMMAR());
            ps.setString(2, "A");
            ps.setInt(3, mar.getIDMAR());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Marca" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificarEst(Marca mar) throws Exception {
               String sql = "update Marca set"
                + " ESTMAR = 'I' where IDMAR=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, mar.getIDMAR());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar MarDAO" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void eliminar(Marca gen) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marca> listar() throws Exception {
                List<Marca> listado = new ArrayList<>();
        Marca mar;
        String sql = "SELECT *\n"
                + "FROM MARCA\n"
                + "WHERE MARCA.ESTMAR = 'A'\n"
                + "ORDER BY NOMMAR DESC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mar = new Marca();
                mar.setIDMAR(rs.getInt("IDMAR"));
                mar.setNOMMAR(rs.getString("NOMMAR"));
                mar.setESTMAR(rs.getString("ESTMAR"));
                listado.add(mar);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lista Marca" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
    }
