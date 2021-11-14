package aplication;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class program {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            //interrogação é um local onde posso preencher
            st.setString(1,"Carl Purple");
            st.setString(2,"Carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1092").getTime()));
            st.setDouble(4,3000);
            st.setInt(5,4);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done!Id = " + id);
                }
            }
            else {
                System.out.println("Done! Rows Affected: " + rowsAffected);
            }
            }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
    }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }}