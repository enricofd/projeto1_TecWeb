package br.edu.insper.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection connection = null;

    public DAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto1", "root", "156748");
    }

    public List<Arquivo> getLista(String category, String order, String search) throws SQLException {

        if (category.equals("NR") && order.equals("NR") && search.equals("NR")) {

            List<Arquivo> arquivos = new ArrayList<Arquivo>();

            java.sql.PreparedStatement stmt = connection.prepareStatement("SELECT * FROM arquivos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Arquivo arquivo = new Arquivo();
                arquivo.setId(rs.getInt("file_id"));
                arquivo.setData(rs.getString("data"));
                arquivo.setCategory(rs.getString("category"));
                arquivo.setUserId(rs.getInt("user_id"));
                arquivo.setTimeStamp(rs.getString("date"));
                arquivos.add(arquivo);
            }

            rs.close();
            stmt.close();

            return arquivos;
        } else if (!category.equals("NR") && order.equals("NR") && search.equals("NR")) {

            List<Arquivo> arquivos = new ArrayList<Arquivo>();

            java.sql.PreparedStatement stmt = connection.prepareStatement("SELECT * FROM arquivos WHERE category = ?");
            stmt.setNString(1, category);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Arquivo arquivo = new Arquivo();
                arquivo.setId(rs.getInt("file_id"));
                arquivo.setData(rs.getString("data"));
                arquivo.setCategory(rs.getString("category"));
                arquivo.setUserId(rs.getInt("user_id"));
                arquivo.setTimeStamp(rs.getString("date"));
                arquivos.add(arquivo);
            }
            rs.close();
            stmt.close();

            return arquivos;

        } else if (category.equals("NR") && !order.equals("NR") && search.equals("NR")) {

            List<Arquivo> arquivos = new ArrayList<Arquivo>();

            if (order.equals("user_id")) {
                java.sql.PreparedStatement stmt = connection
                        .prepareStatement("SELECT * FROM arquivos ORDER BY user_id DESC");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Arquivo arquivo = new Arquivo();
                    arquivo.setId(rs.getInt("file_id"));
                    arquivo.setData(rs.getString("data"));
                    arquivo.setCategory(rs.getString("category"));
                    arquivo.setUserId(rs.getInt("user_id"));
                    arquivo.setTimeStamp(rs.getString("date"));
                    arquivos.add(arquivo);
                }
                rs.close();
                stmt.close();
                return arquivos;
            } else if (order.equals("date")) {
                java.sql.PreparedStatement stmt = connection
                        .prepareStatement("SELECT * FROM arquivos ORDER BY date DESC");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Arquivo arquivo = new Arquivo();
                    arquivo.setId(rs.getInt("file_id"));
                    arquivo.setData(rs.getString("data"));
                    arquivo.setCategory(rs.getString("category"));
                    arquivo.setUserId(rs.getInt("user_id"));
                    arquivo.setTimeStamp(rs.getString("date"));
                    arquivos.add(arquivo);
                }
                rs.close();
                stmt.close();
                return arquivos;
            } else if (order.equals("category")) {
                java.sql.PreparedStatement stmt = connection
                        .prepareStatement("SELECT * FROM arquivos ORDER BY category DESC");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Arquivo arquivo = new Arquivo();
                    arquivo.setId(rs.getInt("file_id"));
                    arquivo.setData(rs.getString("data"));
                    arquivo.setCategory(rs.getString("category"));
                    arquivo.setUserId(rs.getInt("user_id"));
                    arquivo.setTimeStamp(rs.getString("date"));
                    arquivos.add(arquivo);
                }
                rs.close();
                stmt.close();
                return arquivos;
            } else {
                java.sql.PreparedStatement stmt = connection
                        .prepareStatement("SELECT * FROM arquivos ORDER BY file_id DESC");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Arquivo arquivo = new Arquivo();
                    arquivo.setId(rs.getInt("file_id"));
                    arquivo.setData(rs.getString("data"));
                    arquivo.setCategory(rs.getString("category"));
                    arquivo.setUserId(rs.getInt("user_id"));
                    arquivo.setTimeStamp(rs.getString("date"));
                    arquivos.add(arquivo);
                }
                rs.close();
                stmt.close();
                return arquivos;
            }

        } else {
            List<Arquivo> arquivos = new ArrayList<Arquivo>();

            java.sql.PreparedStatement stmt = connection.prepareStatement("SELECT * FROM arquivos WHERE data LIKE ?;");
            stmt.setNString(1, "%" + search + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Arquivo arquivo = new Arquivo();
                arquivo.setId(rs.getInt("file_id"));
                arquivo.setData(rs.getString("data"));
                arquivo.setCategory(rs.getString("category"));
                arquivo.setUserId(rs.getInt("user_id"));
                arquivo.setTimeStamp(rs.getString("date"));
                arquivos.add(arquivo);
            }
            rs.close();
            stmt.close();

            return arquivos;

        }
    }

    public void adiciona(Arquivo arquivo) throws SQLException {
        String sql = "INSERT INTO arquivos (category, data, user_id, date) VALUES(?,?,?,FROM_UNIXTIME(?))";

        long unixTime = Instant.now().getEpochSecond();

        java.sql.PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setNString(1, arquivo.getCategory());
        stmt.setNString(2, arquivo.getData());
        stmt.setNString(3, String.valueOf(arquivo.getUserId()));
        stmt.setLong(4, unixTime);
        stmt.execute();
        stmt.close();
    }

    public void change(String data, String id, String category, String userId) throws SQLException {
        String sql = "UPDATE arquivos SET data = ?, user_id = ?, category = ?, date=FROM_UNIXTIME(?) WHERE file_id = ?;";

        long unixTime = Instant.now().getEpochSecond();

        java.sql.PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setNString(1, data);
        stmt.setNString(2, userId);
        stmt.setNString(3, category);
        stmt.setLong(4, unixTime);
        stmt.setInt(5, Integer.parseInt(id));
        stmt.execute();
        stmt.close();
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void remove(String id) throws SQLException {
        String sql = "DELETE FROM arquivos WHERE file_id=?";

        java.sql.PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(id));
        stmt.execute();
        stmt.close();
    }

    public List<String> getCategories() throws SQLException {

        List<String> categories = new ArrayList<String>();

        java.sql.PreparedStatement stmt = connection.prepareStatement("SELECT DISTINCT category FROM arquivos;");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            categories.add(rs.getString("category"));
        }

        rs.close();
        stmt.close();

        return categories;
    }
}