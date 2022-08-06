import java.sql.*;

public class Application {
    public static void main(String... args) throws SQLException {
        var app = new Application();
        app.setupDb();
    }

    private void setupDb() throws SQLException {
        final var dbUri = "jdbc:h2:mem:;"; //1
        final var initScript = "INIT=runscript from 'create.sql';"; //2

        try( //2
                final var conn = DriverManager.getConnection(dbUri + initScript); //3
                final var query = conn.prepareStatement("SELECT * FROM test");
                final var rs = query.executeQuery()){

            System.out.println(query.getClass().getName());
            System.out.println(conn.getClass().getName());
            System.out.println(rs.getClass().getName());

            while(rs.next()){
                System.out.printf(
                        "id: %d, name: %s%n",
                        rs.getInt(1),
                        rs.getString(2)
                );
            }
        }
    }
}