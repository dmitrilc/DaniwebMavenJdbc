import java.sql.*;

public class Application {
    public static void main(String... args) throws SQLException {
        var app = new Application();
        app.setupDb();
    }

    private void setupDb() throws SQLException {
        final var dbUri = "jdbc:h2:mem:;";
        final var initScript = "INIT=runscript from 'create.sql';";

        try(
                final var conn = DriverManager.getConnection(dbUri + initScript);
                final var query = conn.prepareStatement("SELECT * FROM test");
                final var rs = query.executeQuery()){

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