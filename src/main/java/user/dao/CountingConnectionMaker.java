package user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public final class CountingConnectionMaker implements ConnectionMaker {

    private final ConnectionMaker realConnectionMaker;
    private int counter = 0;

    public CountingConnectionMaker(final ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public final Connection makeConnection() throws ClassNotFoundException, SQLException {
        ++this.counter;
        return realConnectionMaker.makeConnection();
    }

    public final int counter() {
        return counter;
    }
}
