import com.tchristofferson.querybuilder.*;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void testSelectDistinct() {
        String uuid = UUID.randomUUID().toString();
        String sql = new SelectQueryBuilder(true, "name", "money")
                .from("players")
                .where("uuid")
                    .is(uuid)
                    .and("name")
                    .is("bob")
                .toString();

        assertEquals("SELECT DISTINCT name, money FROM players WHERE uuid = '" + uuid + "' AND name = 'bob';", sql);
    }

    @Test
    public void testSelectNoDistinct() {
        String uuid = UUID.randomUUID().toString();
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("uuid")
                .is(uuid)
                .and("name")
                .is("bob")
                .toString();

        assertEquals("SELECT * FROM players WHERE uuid = '" + uuid + "' AND name = 'bob';", sql);
    }

    @Test
    public void testDelete() {
        String uuid = UUID.randomUUID().toString();
        String sql = new DeleteQueryBuilder()
                .from("players")
                .where("uuid")
                .is(uuid)
                .toString();

        assertEquals("DELETE FROM players WHERE uuid = '" + uuid + "';", sql);
    }

    @Test
    public void testUpdate() {
        String sql = new UpdateQueryBuilder("players")
                .set("name", "bob")
                .set("uuid", "uuid")
                .where("uuid")
                    .is(123)
                .toString();

        assertEquals("UPDATE players SET name = 'bob', uuid = 'uuid' WHERE uuid = 123;", sql);
    }

    @Test
    public void testInsert() {
        String sql = new InsertQueryBuilder("players")
                .withColumns("uuid", "name")
                .withValues("uuid", "bob1")
                .withValues("uuid2", "bob2")
                .toString();

        assertEquals("INSERT INTO players (uuid, name) VALUES ('uuid', 'bob1'), ('uuid2', 'bob2');", sql);
    }

    @Test
    public void testCreateTable() {
        String sql = new CreateTableQueryBuilder("saves")
                .withColumn("uuid", "varchar(36)")
                .withColumn("money", "decimal(15, 2)", "NOT NULL")
                .primaryKey("uuid")
                .foreignKey("uuid", "players", "uuid")
                .toString();

        assertEquals("CREATE TABLE saves(uuid varchar(36), money decimal(15, 2) NOT NULL, PRIMARY KEY (uuid), FOREIGN KEY (uuid) REFERENCES players(uuid));", sql);
    }

    @Test
    public void testIsNot() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("name")
                    .isNot("bob")
                .toString();

        assertEquals("SELECT * FROM players WHERE name <> 'bob';", sql);
    }

    @Test
    public void testGreaterThan() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("money")
                    .greaterThan(1000)
                .toString();

        assertEquals("SELECT * FROM players WHERE money > 1000;", sql);
    }

    @Test
    public void testLessThan() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("money")
                    .lessThan(1000)
                .toString();

        assertEquals("SELECT * FROM players WHERE money < 1000;", sql);
    }

    @Test
    public void testGreaterOrEqual() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("money")
                    .greaterOrEqual(1000)
                .toString();

        assertEquals("SELECT * FROM players WHERE money >= 1000;", sql);
    }

    @Test
    public void testLessOrEqual() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("money")
                    .lessOrEqual(1000)
                .toString();

        assertEquals("SELECT * FROM players WHERE money <= 1000;", sql);
    }

    @Test
    public void testIn() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("name")
                    .in("bob", "bobby")
                .toString();

        assertEquals("SELECT * FROM players WHERE name IN ('bob', 'bobby');", sql);
    }

    @Test
    public void testNotIn() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("name")
                    .notIn("bob", "bobby")
                .toString();

        assertEquals("SELECT * FROM players WHERE name NOT IN ('bob', 'bobby');", sql);
    }

    @Test
    public void testBetween() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("money")
                    .between(10000, 20000)
                .toString();

        assertEquals("SELECT * FROM players WHERE money BETWEEN 10000 AND 20000;", sql);
    }

    @Test
    public void testNotBetween() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("money")
                    .notBetween(10000, 20000)
                .toString();

        assertEquals("SELECT * FROM players WHERE money NOT BETWEEN 10000 AND 20000;", sql);
    }

    @Test
    public void testLike() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("name")
                    .like("bob%")
                .toString();

        assertEquals("SELECT * FROM players WHERE name LIKE 'bob%';", sql);
    }

    @Test
    public void testNotLike() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("money")
                    .notLike("bob%")
                .toString();

        assertEquals("SELECT * FROM players WHERE money NOT LIKE 'bob%';", sql);
    }
}
